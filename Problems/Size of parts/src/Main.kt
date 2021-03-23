fun main() {
    val n = readLine()!!.toInt()
    var large = 0
    var small = 0
    var perfect = 0
    for (k in 1..n) {
        val d = readLine()!!.toInt()
        when (d) {
            -1 -> small++
            1 -> large++
            0 -> perfect ++
        }
    }
    println("$perfect $large $small")
}