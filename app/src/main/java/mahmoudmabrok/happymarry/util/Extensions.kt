package mahmoudmabrok.happymarry.util

fun String.getID(): String {
    val first = this.indexOfFirst { it.toString() == "=" } + 1
    val last = this.indexOfFirst { it.toString() == "&" }
    return this.substring(first, last.takeIf { it > 0 } ?: this.length)
}