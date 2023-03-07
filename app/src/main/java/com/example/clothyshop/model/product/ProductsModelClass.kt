package com.example.clothyshop.model.product

data class ProductsModelClass(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)