package com.example.clothyshop.model.productdetails

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productdetails")
data class ProductDetailsModel(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)