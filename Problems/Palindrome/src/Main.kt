import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val str = scanner.next()
    var str2 = ""
    for (k in str.length-1 downTo 0) {
        str2 += str[k]
    }
    println(if (str == str2) "yes" else "no")  
}
