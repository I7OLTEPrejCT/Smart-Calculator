fun main() {
    val words = mutableMapOf<String, Int?>()
    while (true) {
        val input = readLine()!!.toString()
        when {
            input == "stop" -> break
            input == "stop" && words.isEmpty() -> {
                println("null")
                break
            }
            !words.containsKey(input) -> words.putIfAbsent(input, 1)
            words.containsKey(input) -> {
                val count: Int? = words[input]?.inc()
                words[input] = count
            }
        }
    }
    print(words.maxByOrNull { it.value!! }?.key)
}