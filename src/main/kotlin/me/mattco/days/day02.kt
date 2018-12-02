package me.mattco.days

import me.mattco.utils.ResourceLoader.getTextResource

object Day2 {
    private val input = getTextResource("/day02")

    fun part1(): Any? {
        var two = 0
        var three = 0

        input.split("\r\n").map {
            val counts = it.groupingBy { it }.eachCount()
            if (counts.containsValue(2))
                two++
            if (counts.containsValue(3))
                three++
        }

        return two * three
    }

    fun part2(): Any? {
        val ids = input.split("\r\n")

        for (id in ids) {
            for (idO in ids) {
                var diff = 0
                for (i in id.indices) {
                    if (id[i] != idO[i])
                        diff++
                }
                if (diff == 1) {
                    return id.filterIndexed { index, c -> idO[index] == c }
                }
            }
        }

        return -1
    }
}

fun main() {
    println("=== Day 2 ===")
    println("Part 1: ${Day2.part1()}")
    println("Part 2: ${Day2.part2()}")
}
