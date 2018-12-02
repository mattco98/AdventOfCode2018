package me.mattco.daysOptimal

import me.mattco.utils.ResourceLoader.getTextResource

object Day2 {
    private val input = getTextResource("/day02").split("\r\n")

    fun part1() = input.map {
        it.groupingBy { it }.eachCount().let { counts ->
            counts.containsValue(2) to counts.containsValue(3)
        }
    }.run {
        count { p -> p.first } * count { p -> p.second }
    }

    fun part2() = input.run {
        filter { id ->
            any { it.zip(id).count { it.first != it.second } == 1 }
        }.run {
            this[0].filterIndexed { i, c -> this[1][i] == c }
        }
    }
}

fun main() {
    println("=== Day 2 ===")
    println("Part 1: ${Day2.part1()}")
    println("Part 2: ${Day2.part2()}")
}
