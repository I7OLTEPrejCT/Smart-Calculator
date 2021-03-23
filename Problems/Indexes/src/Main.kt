fun solution(products: List<String>, product: String) {
    for (p in 0..products.lastIndex) {
        if (products[p] == product) print("$p ")
    }
}