package util

fun isValidInput(text: String): Boolean {
    return text.replace("\\s".toRegex(), "") != ""
}