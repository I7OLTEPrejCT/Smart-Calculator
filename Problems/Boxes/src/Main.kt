import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val box1 = IntArray(3) { scanner.nextInt() }
    val box2 = IntArray(3) { scanner.nextInt() }
    box1.sort()
    box2.sort()
    val res = IntArray(3) { 0 }

    for (i in 0..box1.lastIndex) {
        when {
            box1[i] == box2[i] -> res[i] = 0
            box1[i] < box2[i] -> res[i] = 1
            box1[i] > box2[i] -> res[i] = -1
        }
    }

    when {
        res.contains(-1) && res.contains(1) -> println("Incomparable")
        res.all { it == 0 } -> println("Box 1 = Box 2")
        res.all { it != -1 } -> println("Box 1 < Box 2")
        res.all { it != 1 } -> println("Box 1 > Box 2")
    }
}