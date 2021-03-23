fun main() {
    val str = readLine()!!.toString()
    for (i in str) {
        if(i.isDigit()) {
            println(i)
            break
        }
    }
}