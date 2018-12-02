package me.mattco.days

import me.mattco.utils.ResourceLoader.getTextResource

object Day1 {
    private val input = getTextResource("/day01")

    fun part1(): Any? {
        var freq = 0

        input.split("\r\n").forEach { line ->
            var t = line.substring(1, line.length).toInt()
            if (line[0] == '-') t *= -1
            freq += t
        }

        return freq
    }

    fun part2(): Any? {
        var freq = 0
        val freqs = mutableSetOf<Int>()

        while (true) {
            input.split("\r\n").forEach { line ->
                var t = line.substring(1, line.length).toInt()
                if (line[0] == '-') t *= -1
                freq += t
                if (!freqs.add(freq))
                    return freq
            }
        }
    }
}

fun main() {
    println("=== Day 1 ===")
    println("Part 1: ${Day1.part1()}")
    println("Part 2: ${Day1.part2()}")
}
