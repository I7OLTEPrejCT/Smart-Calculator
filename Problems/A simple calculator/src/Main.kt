fun main() {
    val (dig1, oper, dig2) = readLine()!!.split(" ")
    println(when (oper) {
        "+" -> dig1.toLong() + dig2.toLong()
        "-" -> dig1.toLong() - dig2.toLong()
        "*" -> dig1.toLong() * dig2.toLong()
        "/" -> {
            if (dig2 == "0") "Division by 0!"
            else dig1.toLong() / dig2.toLong()
        }
        else -> "Unknown operator"
    })
}
