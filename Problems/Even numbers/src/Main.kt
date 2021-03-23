/**
 * My solution
fun solution(numbers: List<Int>) {
    for (number in numbers) {
        print(if (number % 2 == 0) "$number " else continue)
    }
}
 */
// Best solution
fun solution(numbers: List<Int>) {
    println(numbers.filter { it % 2 == 0 }.joinToString(" "))
}