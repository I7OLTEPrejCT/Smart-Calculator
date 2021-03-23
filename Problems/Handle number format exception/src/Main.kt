fun parseCardNumber(cardNumber: String): Long {
    val spaceCount = cardNumber.length - cardNumber.replace(" ", "").length
    if (spaceCount != 3 && cardNumber.length != 19) {
        throw Exception("Wrong card number")
    }
    return cardNumber.replace(" ", "").toLong()
}
