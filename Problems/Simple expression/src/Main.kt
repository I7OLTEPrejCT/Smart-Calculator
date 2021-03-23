fun main() {
    val (a, b, c, d) = Array(4) { readLine()!!.toBigInteger() }
    println(-a * b + c - d)
}
