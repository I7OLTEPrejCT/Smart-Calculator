import java.math.BigInteger
import kotlin.math.floor

fun main() {
    var (a, b) = Array(2) { readLine()!!.toBigInteger() }
    val sum = a + b
    a *= 100.toBigInteger()
    b *= 100.toBigInteger()
    println("${(a / sum)}% ${(b / sum)}%" )
}