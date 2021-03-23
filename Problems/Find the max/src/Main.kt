fun main() {
    var max = Int.MIN_VALUE
    var index = 0
    for (i in 1..readLine()!!.toInt()) {
        val input = readLine()!!.toInt()
        if (input > max) {
            max = input
            index = i - 1
        }
    }
    println(index)
}

/**
 * Вариант лучше:
    fun main() {
        val nums = Array(readLine()!!.toInt()) { readLine()!!.toInt() }
        println(nums.indexOfFirst { it == nums.maxOrNull() })
    }
 *
 * И ещё один:
    fun main() = print(Array(readLine()!!.toInt()) { readLine()!!.toInt() }.run { this.indexOf(this.maxOrNull()) })
 *
 */