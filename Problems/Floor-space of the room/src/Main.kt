import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val roomType = readLine()!!
    println(when (roomType) {
        "triangle" -> {
            val a = readLine()!!.toDouble()
            val b = readLine()!!.toDouble()
            val c = readLine()!!.toDouble()
            val p = (a + b + c) / 2
            sqrt(p * (p - a) * (p - b) * (p - c))
        }
        "rectangle" -> readLine()!!.toDouble() * readLine()!!.toDouble()
        "circle" -> 3.14 * readLine()!!.toDouble().pow(2)
        else -> ""
    })
}