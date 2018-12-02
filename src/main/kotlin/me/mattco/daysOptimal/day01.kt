package me.mattco.daysOptimal

import me.mattco.utils.ResourceLoader.getTextResource

object Day1 {
    private val input = getTextResource("/day01")

    fun part1() = input.split("\r\n").map { it.toInt() }.sum()

    fun part2(): Any? {
        var freq = 0
        val freqs = mutableSetOf<Int>()

        while (true) {
            input.split("\r\n").forEach {
                freq += it.toInt()
                if (!freqs.add(freq)) return freq
            }
        }
    }
}

fun main() {
    println("=== Day 1 ===")
    println("Part 1: ${Day1.part1()}")
    println("Part 2: ${Day1.part2()}")
}
