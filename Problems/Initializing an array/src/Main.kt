import java.util.*

fun main () {
    var beyondTheWall = emptyArray<String>()
    var backFromTheWall = emptyArray<String>()
    val scanner = Scanner(System.`in`)

    while (scanner.hasNext()) {
        beyondTheWall += scanner.next()
    }
    while (scanner.hasNext()) {
        backFromTheWall += scanner.next()

    }

    println(beyondTheWall.contentEquals(backFromTheWall))
}