fun main() {
    val owls = mutableMapOf('a' to 1, 'e' to 5, 'i' to 9, 'o' to 15, 'u' to 21)
    val letter = readLine()!!.toString().first().toLowerCase()
    println(if (owls.containsKey(letter)) owls[letter] else 0)
}