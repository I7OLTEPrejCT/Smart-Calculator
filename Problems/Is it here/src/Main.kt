fun main() {
    /*
    val n = readLine()!!.toInt()
    val k = IntArray(n)
    var flag = false
    for (c in 0 until n) {
        k[c] = readLine()!!.toInt()
    }
    val m = readLine()!!.toInt()
    for (c in 0 until n) {
        if (k[c] == m) {
            flag = true
            break
        }
    }
    if (flag) {
        println("YES")
    } else { println("NO")}

     */

    // Beautiful version
    val numbers = IntArray(readLine()!!.toInt()) { readLine()!!.toInt() }
    println(if (readLine()!!.toInt() in numbers) "YES" else "NO")
}