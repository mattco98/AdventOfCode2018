package me.mattco.daysOptimal

import me.mattco.utils.ResourceLoader.getTextResource
import me.mattco.utils.lower
import me.mattco.utils.reduce
import me.mattco.utils.removeAll

object Day5 {
    private val input = getTextResource("/day05")

    private fun react(input: String) = input.reduce("") { prev, ch ->
        if (prev.isEmpty())
            ch.toString()
        else if (prev.last() == ch + 0x20 || prev.last() == ch - 0x20)
            prev.substring(0 until prev.length - 1)
        else
            prev + ch
    }

    fun part1() = react(input).length

    fun part2() = ('A'..'Z')
            .map { input.removeAll(it).removeAll(it.lower()) }
            .map { react(it).length }
            .min()!!
}

fun main() {
    println("=== Day 5 ===")
    println("Part 1: ${Day5.part1()}")
    println("Part 2: ${Day5.part2()}")
}
