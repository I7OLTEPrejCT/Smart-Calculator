fun main() {
    val input = readLine()!!.toString()
    println(when {
        input.isEmpty() || input.isBlank() -> input
        input.first() == 'i' -> input.drop(1).toInt() + 1
        input.first() == 's' -> input.drop(1).reversed()
        else -> input
    })
}