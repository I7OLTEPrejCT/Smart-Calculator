package calculator

import java.lang.NumberFormatException
import java.math.BigInteger
import kotlin.math.pow

private var stack = mutableListOf<String>()
private var queue = mutableListOf<String>()
private var expressionList = mutableListOf<String>()
private val inputMap = mutableMapOf<String, BigInteger>()

fun main() {
    val operators = Regex("[-+/*^]")
    while (true) {
        var input = readLine()!!.toString().replace(" ", "")
        stack.clear()
        queue.clear()
        expressionList.clear()
        try {
            when {
                input.startsWith("/") ->
                    when (input) {
                        "/help" -> println("The program is an awesome calculator!")
                        "/exit" -> {
                            println("Bye!")
                            break
                        }
                        else -> println("Unknown command")
                    }
                input.isEmpty() || input.isBlank() -> continue
                input.contains("[а-яА-Я]".toRegex()) -> throw NumberFormatException()
                input.contains("([a-zA-Z]+\\d+)=.*".toRegex()) -> println("Invalid identifier")
                input.matches("([a-zA-Z])+=(\\d*[a-zA-Z]+\\d+[a-zA-Z]*)+".toRegex()) -> println("Invalid assignment")
                input.matches("([a-zA-Z])+=(\\d+[a-zA-Z]+)+".toRegex()) -> println("Invalid assignment")
                input.contains("([a-zA-Z])+=.*=+".toRegex()) -> println("Invalid assignment")
                input.filter { ch -> ch == '(' }.count() != input.filter { ch -> ch == ')' }.count() -> throw NumberFormatException()
                input.matches("([a-zA-Z])+=([a-zA-Z])+".toRegex())
                        || input.matches("([a-zA-Z])+=(-*\\d+)".toRegex()) -> {
                    val arr: Array<String> = input.replace(" ", "").split("=").toTypedArray()
                    when {
                        arr[1].contains("\\d".toRegex()) -> inputMap.put(arr[0], arr[1].toBigInteger())
                        arr[1].contains("([a-zA-Z]*\\D)+".toRegex())
                                && inputMap.containsKey(arr[1]) -> inputMap[arr[0]] = inputMap[arr[1]]!!
                        else -> println("Unknown variable")
                    }
//                    println(inputMap)
                }

                input.contains(operators) -> {

                    if (input.contains("(--)+".toRegex())) input = input.replace("(--)+".toRegex(),"+")
                    if (input.contains("\\+*\\++\\+*".toRegex()))  input = input.replace("\\+*\\++\\+*".toRegex(),"+")
                    if (input.contains("(-*\\+-)+".toRegex())) input = input.replace("(-*\\+-)+".toRegex(),"-")
                    if (input.contains("(\\*\\*)".toRegex())) throw NumberFormatException()
                    if (input.contains("(//)".toRegex())) throw NumberFormatException()
                    //if (input.contains("\\**\\*+\\**".toRegex()))  input = input.replace("\\**\\*+\\**".toRegex(),"*")
                    //if (input.contains("/*/+/*".toRegex())) input = input.replace("/*/+/*".toRegex(),"/")

                    var digit = ""
                    var length = 0
                    input.forEach {
                        if (!Regex("[-+/*()^]").containsMatchIn(it.toString())) {
                            digit += it
                            if (length == input.lastIndex) {
                                checkElem(digit)
                            }
                        } else {
                            checkElem(digit)
                            expressionList.add(it.toString())
                            digit = ""
                        }
                        length++
                    }

                    getPostFixEx()
                    calcPostFix()

                }
                inputMap.containsKey(input) -> println(inputMap[input])
                !inputMap.containsKey(input) -> println("Unknown variable")
                !input.contains(" ") -> println(input.toInt())
            }

        } catch (e: NumberFormatException) {
            println("Invalid expression")
        }
    }
}

private fun checkElem (digit: String) {
    if (Regex("[a-zA-z]").containsMatchIn(digit)
        && inputMap.containsKey(digit)
    ) {
        expressionList.add(inputMap[digit].toString())
    } else if (Regex("[a-zA-z]").containsMatchIn(digit)
        && !inputMap.containsKey(digit)
    ) {
        throw NumberFormatException()
    } else {
        expressionList.add(digit)
    }
}

private fun getPostFixEx() {
    // Перебираем expressionList
    expressionList.forEach {
        when {
            //Если входящий элемент левая скобка делаем PUSH
            it == "(" -> push(it)

            //Если входящий элемент правая скобка делаем POP
            it == ")" -> {
                if (expressionList.contains("(")) {
                    pop()
                }
            }

            //Если входящий элемент число, то добавляем в очередь
            Regex("[\\d]").containsMatchIn(it) -> addQueue(it)

            //Если входящий элемент + или -
            Regex("[+-]").containsMatchIn(it) ->
                /* Проверяем, если стек пустой или на вершине стека левая скобка,
                * то делаем PUSH */
                if (stack.isEmpty() || stack.last() == "(") push(it)
                /* Иначе, если на вершине стека оператор имеющий больший
                * приоритет, то делаем POP, потом PUSH */
                else if (stack.last().contains(Regex("[/*]"))) {
                    pop()
                    push(it)
                }
                // Иначе просто делаем PUSH
                else {
                    addQueue(stack.last())
                    stack[stack.lastIndex] = it
                }

            //Если входящий элемент * или /
            Regex("[*/]").containsMatchIn(it) -> {
                /* Проверяем, если на вершине стека элемент с таким же приоритетом,
                * то делаем POP */
                if (stack.isNotEmpty() && (stack.last() == "*" || stack.last() == "/")) {
                    pop()
                    push(it)
                }
                /* Иначе, если на вершине стека оператор имеющий больший
                * приоритет, то делаем POP, потом PUSH */
                else if (stack.isNotEmpty() && stack.last().contains(Regex("[\\^]"))) {
                    pop()
                    push(it)
                }
                // Иначе просто делаем PUSH
                else {
                    push(it)
                }
            }

            Regex("[\\^]").containsMatchIn(it) -> {
                /* Проверяем, если на вершине стека элемент с таким же приоритетом,
                * то делаем POP */
                if (stack.isNotEmpty() && (stack.last() == "^")) {
                    pop()
                }
                push(it)
            }
        }
    }
    // Когда перебрали все элементы выражения, то добавляем из стека элементы в очередь
    if (stack.isNotEmpty()) {
        for (i in stack.lastIndex downTo 0) {
            if (stack[i] != "(") {
                addQueue(stack[i])
            }
        }
    }
}

private fun pop() {
    // Выгружаем стек в очередь пока не найдем левую скобку, потом удаляем скобку из стека
    Loop@ for (i in stack.lastIndex downTo 0) {
        if (stack[i] == "(") {
            stack[i] = " "
            break@Loop
        }
        addQueue(stack[i])
        stack[i] = " "
    }
    stack.removeIf { it == " " }
}

private fun addQueue(item: String) {
    queue.add(item)
}

private fun push(item: String) {
    stack.add(item)
}

private fun calcPostFix() {
    //Создаем стек для работы
    val stack = mutableListOf<BigInteger>()
    // Перебераем все эелементы очереди
    for (item in queue) {
        when {
            // Если входящий элемент - число, то добавляем в стек
            Regex("[\\d]").containsMatchIn(item) -> {
                stack.add(item.toBigInteger())
            }
            /* Если входящий элемент + , берем два последних элемента
            и производим советующую операцию */
            item == "+" -> {
                stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] + stack.last()
                stack.removeAt(stack.lastIndex)
            }
            /* Если входящий элемент * , берем два последних элемента
            и производим советующую операцию */
            item == "*" -> {
                stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] * stack.last()
                stack.removeAt(stack.lastIndex)
            }
            /* Если входящий элемент / , берем два последних элемента
            и производим советующую операцию */
            item == "/" -> {
                stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] / stack.last()
                stack.removeAt(stack.lastIndex)
            }
            /* Если входящий элемент -, берем два последних элемента
             и производим советующую операцию */
            item == "-" -> {
                stack[stack.lastIndex - 1] = stack[stack.lastIndex - 1] - stack.last()
                stack.removeAt(stack.lastIndex)
            }
            /* Если входящий элемент ^ , берем два последних элемента
            и производим советующую операцию */
            item == "^" -> {
                val str = stack[stack.lastIndex - 1].toDouble().pow(stack.last().toDouble()).toString()
                stack[stack.lastIndex - 1] = str.replace("\\..*".toRegex(), "").toBigInteger()
                stack.removeAt(stack.lastIndex)
            }
        }
    }



    // Результат - это элемент, который остался в стеке
    println("${stack.first()}")
}