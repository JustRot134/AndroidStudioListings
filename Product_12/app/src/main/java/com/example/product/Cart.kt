package com.example.product

class Cart {
    // Список товаров в корзине. private — снаружи его менять нельзя
    private val items = mutableListOf<Product>()

    fun add(product: Product) {
        items.add(product)
    }

    fun count(): Int {
        return items.size
    }

    fun newPrice(product: Product): Int {
        val discount = 1 - (product.discount.toDouble() / 100)
        return (product.price * discount).toInt()
    }

    fun total(): Int {
        var sum = 0
        for (item in items) {
            sum += item.price
        }
        return sum
    }

    fun clear() {
        items.clear()
    }
}