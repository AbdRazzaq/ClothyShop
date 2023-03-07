package com.example.clothyshop.model.cart



data class CartModelClass(
    val discountedTotal: Int,
    val id: Int,
    val products: List<CartProduct>,
    val total: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)