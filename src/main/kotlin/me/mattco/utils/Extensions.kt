package me.mattco.utils

///////////
/// Any ///
///////////
fun Any?.print() {
    println(this)
}

////////////
/// Char ///
////////////
fun Char.lower() = this.toLowerCase()
fun Char.upper() = this.toUpperCase()

//////////////
/// String ///
//////////////
fun String.lower() = this.toLowerCase()
fun String.upper() = this.toUpperCase()

fun String.with(index: Int, value: Char): String {
    val newStr = if (index < length) this else {
        this + " ".repeat(index - length + 1)
    }

    return newStr.substring(0, index) + value + newStr.substring(index + 1, newStr.length)
}

fun String.removeAll(ch: Char) = this.replace(ch.toString(), "")

fun <T> String.reduce(default: T, fn: (T, Char) -> T): T {
    var v = default
    this.forEach { v = fn(v, it) }
    return v
}

operator fun String.get(range: IntRange) = substring(range)

/////////////////
/// Iterables ///
/////////////////
fun <T> Iterable<T>.printAll() {
    this.forEach { println(it) }
}

fun <T, R> Iterable<T>.reduce(default: R, fn: (R, T) -> R): R {
    var v = default
    this.forEach { v = fn(v, it) }
    return v
}

fun <T> Iterable<T>.cycle() = generateSequence { this }.flatten()

fun <T : Number> Iterable<T>.toInts() = map { it.toInt() }

fun <T : Number> Iterable<T>.toDoubles() = map { it.toDouble() }

operator fun <T> List<T>.get(indices: IntRange) = slice(indices)


//////////////////////////////
/// Multidimensional lists ///
//////////////////////////////

typealias List2D<T> = List<List<T>>
typealias List3D<T> = List<List<List<T>>>
typealias List4D<T> = List<List<List<List<T>>>>
typealias MutableList2D<T> = MutableList<MutableList<T>>
typealias MutableList3D<T> = MutableList<MutableList<MutableList<T>>>
typealias MutableList4D<T> = MutableList<MutableList<MutableList<MutableList<T>>>>

operator fun <T> List2D<T>.get(i: Int, j: Int) = this[i][j]
operator fun <T> List3D<T>.get(i: Int, j: Int, k: Int) = this[i][j][k]
operator fun <T> List4D<T>.get(i: Int, j: Int, k: Int, l: Int) = this[i][j][k][l]
operator fun <T> MutableList2D<T>.set(i: Int, j: Int, el: T) {
    this[i][j] = el
}

operator fun <T> MutableList3D<T>.set(i: Int, j: Int, k: Int, el: T) {
    this[i][j][k] = el
}

operator fun <T> MutableList4D<T>.set(i: Int, j: Int, k: Int, l: Int, el: T) {
    this[i][j][k][l] = el
}

fun <T> listOf2D(vararg elements: List<T>) = if (elements.isEmpty()) elements.asList() else emptyList()
fun <T> listOf3D(vararg elements: List2D<T>) = if (elements.isEmpty()) elements.asList() else emptyList()
fun <T> listOf4D(vararg elements: List3D<T>) = if (elements.isEmpty()) elements.asList() else emptyList()
fun <T> mutableListOf2D(vararg elements: MutableList<T>) = if (elements.isEmpty()) elements.asList() else emptyList()
fun <T> mutableListOf3D(vararg elements: MutableList2D<T>) = if (elements.isEmpty()) elements.asList() else emptyList()
fun <T> mutableListOf4D(vararg elements: MutableList3D<T>) = if (elements.isEmpty()) elements.asList() else emptyList()
fun <T> mutableListOf2D(): MutableList2D<T> = ArrayList(ArrayList())
fun <T> mutableListOf3D(): MutableList3D<T> = ArrayList(ArrayList(ArrayList()))
fun <T> mutableListOf4D(): MutableList4D<T> = ArrayList(ArrayList(ArrayList(ArrayList())))
fun <T> mutableListOf2D(size: Int, default: T): MutableList2D<T> = MutableList(size) { MutableList(size) { default } }
fun <T> mutableListOf3D(size: Int, default: T): MutableList3D<T> = MutableList(size) { MutableList(size) { MutableList(size) { default }}}
fun <T> mutableListOf4D(size: Int, default: T): MutableList4D<T> = MutableList(size) { MutableList(size) { MutableList(size) { MutableList(size) { default }}}}

operator fun <T> List2D<T>.get(i: IntRange, j: IntRange) = slice(i).map { it.slice(j) }
operator fun <T> List3D<T>.get(i: IntRange, j: IntRange, k: IntRange) = slice(i).map { it.slice(j).map { it.slice(k) } }
operator fun <T> List4D<T>.get(i: IntRange, j: IntRange, k: IntRange, l: IntRange) = slice(i).map { it.slice(j).map { it.slice(k).map { it.slice(l) } } }
