package me.mattco.days

import me.mattco.utils.ResourceLoader.getTextResource
import java.lang.Math.abs
import kotlin.math.max

object Day6 {
    private val input = getTextResource("/day06").split(System.lineSeparator())

    private fun getGrid(): List<Pair<Int, Int>> = input.map {
        val v = it.split(", ")
        v[0].toInt() to v[1].toInt()
    }

    private fun dist(one: Pair<Int, Int>, two: Pair<Int, Int>): Int {
        return abs(one.first - two.first) + abs(one.second - two.second)
    }

    fun part1(): Any? {
        val g = getGrid()
        val names = mutableMapOf<Pair<Int, Int>, Int>()
        g.forEachIndexed { index, pair -> names[pair] = index }

        val m = names.maxBy { max(it.key.first, it.key.second) }!!
        val max = max(m.key.first, m.key.second)

        var grid = mutableMapOf<Pair<Int, Int>, Int>()

        for (i in 0..max) {
            for (j in 0..max) {
                val distances = names.map { it.value to dist(i to j, it.key) }
                val min = distances.minBy { it.second }!!
                if (distances.map { it.second }.count { it == min.second } > 1) {
                    grid[i to j] = -1
                    continue
                }

                grid[i to j] = min.first
            }
        }

        for (i in 0..max) {
            for (j in 0..max) {
                if (!(i == 0 || i == max || j == 0 || j == max)) continue

                val v = grid[i to j]
                if (v != -1) {
                    grid = grid.filter { it.value != v }.toMutableMap()
                }
            }
        }

        return grid.values.filter { it != -1 }.groupingBy { it }.eachCount().values.max()
    }

    fun part2(): Any? {
        val g = getGrid()

        val max = max(g.maxBy { it.first }!!.first, g.maxBy { it.second }!!.second)

        var grid = mutableMapOf<Pair<Int, Int>, Int>()

        for (i in 0..max) {
            for (j in 0..max) {
                for (p in g) {
                    val pair = i to j
                    grid[pair] = grid.getOrDefault(pair, 0) + dist(pair, p)
                }
            }
        }

        return grid.filter { it.value < 10000 }.count()
    }
}

fun main() {
    println("=== Day 6 ===")
    println("Part 1: ${Day6.part1()}")
    println("Part 2: ${Day6.part2()}")
}
