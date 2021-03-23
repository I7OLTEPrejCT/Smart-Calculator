fun main() {
    val b = readLine()!!
    for (res in 'a'..'z') {
        if (res.toString() == b) return
        print(res)
    }
}
