package util

fun String.replaceLast(old: String, new: String)
= this.reversed().replaceFirst(old, new).reversed()

fun String.removeLast(s: String) = this.replaceLast(s, "")
fun String.isDigit() = this.fold(true) { acc, c -> acc && c.isDigit() }