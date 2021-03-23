fun main() {
    val report = readLine()!!.toString()
    val regex = Regex(". wrong answers?")
    println(report.matches(regex))
}