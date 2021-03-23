fun solution(strings: MutableList<String>, str: String): MutableList<String> {
    for (p in strings.indices) {
        if (strings[p] == str) strings[p] = "Banana"
    }
    return strings
}
//Better solution
//fun solution(strings: MutableList<String>, str: String): MutableList<String> =
//    strings.map { if (it == str) "Banana" else it }.toMutableList()