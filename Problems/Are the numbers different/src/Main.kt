fun main() {
    val arr = IntArray(3) { readLine()!!.toInt() }
    println(arr[0] != arr[1] && arr[0] != arr[2] && arr[1] != arr[2])
}