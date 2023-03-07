package com.example.clothyshop.model.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartProduct(
    val discountPercentage: Double,
    val discountedPrice: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val total: Int
)