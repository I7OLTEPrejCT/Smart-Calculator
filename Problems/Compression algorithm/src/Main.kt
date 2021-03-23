import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    if (scanner.hasNext()) {
        val str = scanner.next()!!.toCharArray()
        var ch = str[0]
        var count = 0
        var res = ""
        for (k in 0..str.lastIndex) {
            if (ch == str[k]) {
                count++
                if (k == str.lastIndex) {
                    res += ch + "$count"
                }
            } else {
                res += ch
                res += "$count"
                ch = str[k]
                count = 1
                if (k == str.lastIndex) {
                    res += ch + "$count"
                }
            }
        }
        println(res)
    } else { println () }
}