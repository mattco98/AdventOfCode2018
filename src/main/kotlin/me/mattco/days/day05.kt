package me.mattco.days

import me.mattco.utils.ResourceLoader.getTextResource

object Day5 {
    private val input = getTextResource("/day05")

    private fun trim(input: String): String {
        var t = input
        for (i in 0 until t.length - 1) {
            if (t[i + 1] == t[i] + 0x20 || t[i + 1] == t[i] - 0x20) {
                t = t.removeRange(i..i+1)
                break
            }
        }
        return t
    }

    private fun react(input: String): String {
        var l = input.length
        var t = input
        while (true) {
            t = trim(t)
            if (t.length == l) {
                return t
            } else {
                l = t.length
            }
        }
    }

    fun part1(): Any? {
        return react(input).length
    }

    fun part2(): Any? {
        val z = mutableMapOf<Char, Int>()
        for (i in 'A'..'Z') {
            val t = input.replace(i.toString(), "").replace((i + 0x20).toString(), "")
            z[i] = react(t).length
        }

        return z.values.min()!!
    }
}

fun main() {
    println("=== Day 5 ===")
    println("Part 1: ${Day5.part1()}")
    println("Part 2: ${Day5.part2()}")
}
