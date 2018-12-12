package me.mattco.days

import me.mattco.utils.ResourceLoader.getTextResource
import java.util.*

object Day4 {
    private val input = getTextResource("/day04").split(System.lineSeparator())

    enum class Action {
        BEGIN_SHIFT,
        FALL_ASLEEP,
        WAKE_UP
    }

    data class Instruction(val date: Calendar, val action: Action, val guardNumber: Int?)

    data class Guard(val id: Int, val asleep: MutableMap<Int, Int> = mutableMapOf())

    private fun getInstructions() = input.map { line ->
        Regex("""^\[(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})] (.+)$""").matchEntire(line)!!.groupValues.let { matches ->
            val action = when {
                matches[6].startsWith("Guard") -> Action.BEGIN_SHIFT
                matches[6] == "falls asleep" -> Action.FALL_ASLEEP
                matches[6] == "wakes up" -> Action.WAKE_UP
                else -> throw Exception(":(")
            }

            val guardNumber = if (action == Action.BEGIN_SHIFT) Regex("(\\d+)").find(matches[6])!!.groupValues[1].toInt() else null

            Instruction(
                Calendar.getInstance().apply {
                    set(matches[1].toInt(), matches[2].toInt(), matches[3].toInt(), matches[4].toInt(), matches[5].toInt())
                },
                action,
                guardNumber
            )
        }
    }.sortedBy {
        it.date
    }

    private fun getGuards(): MutableList<Guard> {
        val guards = mutableListOf<Guard>()
        var guardId = -1
        var begin = -1

        getInstructions().forEach { inst ->
            when (inst.action) {
                Action.BEGIN_SHIFT -> guardId = inst.guardNumber!!
                Action.FALL_ASLEEP -> begin = inst.date.get(Calendar.MINUTE)
                Action.WAKE_UP -> {
                    var guard = guards.find { it.id == guardId }
                    if (guard == null) {
                        guard = Guard(guardId)
                        guards.add(guard)
                    }

                    for (i in begin until inst.date.get(Calendar.MINUTE))
                        guard.asleep[i] = guard.asleep.getOrDefault(i, 0) + 1
                }
            }
        }

        return guards
    }

    fun part1(): Any? {
        val guards = getGuards()

        val id = guards.maxBy { it.asleep.values.sum() }!!.id
        val minute = guards.find { it.id == id }!!.asleep.maxBy { it.value }!!.key

        return id * minute
    }

    fun part2(): Any? {
        val guards = getGuards()

        val guard = guards.maxBy { it.asleep.values.max()!! }!!
        val minute = guard.asleep.maxBy { it.value }!!.key

        return guard.id * minute
    }
}

fun main() {
    println("=== Day 4 ===")
    println("Part 1: ${Day4.part1()}")
    println("Part 2: ${Day4.part2()}")
}
