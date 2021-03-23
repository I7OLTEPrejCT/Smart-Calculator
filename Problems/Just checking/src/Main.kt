import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val numbers = IntArray(scanner.nextInt()) { scanner.nextInt() }
    var here = false
    val p = scanner.nextInt()
    val m = scanner.nextInt()
    for (k in 0 until numbers.lastIndex) {
        if (numbers[k] == p && numbers[k + 1] == m
            || numbers[k+1] == p && numbers[k] == m) here = true
    }
    println(if (here) "YES" else "NO")
}
