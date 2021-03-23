fun main() {
    val numbers = IntArray(readLine()!!.toInt()) { readLine()!!.toInt() }
    var count = 0
    for (k in 0 until numbers.lastIndex - 1) {
        if (numbers[k] == numbers[k+1]-1 && numbers[k] == numbers[k+2]-2) ++count
    }
    println(count)
}
