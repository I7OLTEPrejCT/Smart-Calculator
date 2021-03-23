fun main() {
    val url = readLine()!!.toString()
//    val url = "https://target.com/index.html?pass=12345&port=8080&cookie=&host=localhost"
    val parts: Array<String> = url.split("?", "&").toTypedArray()
    var passValue = ""
    var pass = false
    for (k in 1..parts.lastIndex) {
        val cut: Array<String> = parts[k].split("=").toTypedArray()
        if (cut[1] == "") { cut[1] = "not found" }
        if (cut[0] == "pass") {
            passValue = cut[1]
            pass = true
        }
        println("${cut[0]} : ${cut[1]}")
        cut.sumOf { it.toInt() }
    }
    if (pass) { println("password : $passValue") }
}