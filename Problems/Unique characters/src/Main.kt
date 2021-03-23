fun main() {
    val str = readLine()!!.toString()
    var count = 0
    var res = 0
    for (i in str) {
        for (j in str) {
            if (j == i) count++
        }
        if (count == 1) res++
        count = 0
    }
    println(res)
}