package me.mattco.utils

object ResourceLoader {
    fun getTextResource(location: String): String {
        return this::class.java.getResource(location).readText()
    }

    fun getIntResource(location: String): Int {
        return getTextResource(location).toInt()
    }
}