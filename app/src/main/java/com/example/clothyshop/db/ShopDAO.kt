package com.example.clothyshop.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clothyshop.model.cart.CartProduct
import com.example.clothyshop.model.product.Product
import com.example.clothyshop.model.productdetails.ProductDetailsModel
import com.example.clothyshop.model.profile.ProfileModelClass

@Dao
interface ShopDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(products : List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(products : ProfileModelClass)

    @Query("SELECT * FROM products")
    suspend fun getProducts() : List<Product>

    @Query("SELECT id,firstName,lastName,maidenName,email,gender,image,phone,age,birthDate,bloodGroup,domain,ein,eyeColor,height,ip,macAddress,password,ssn,university,userAgent,username,weight FROM profile")
    suspend fun getUser() : ProfileModelClass

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductsDetails(productDetails : ProductDetailsModel)

    @Query("SELECT * FROM productdetails")
    suspend fun getProductDetails() : ProductDetailsModel

    @Query("SELECT * FROM cart")
    suspend fun getCart() : List<CartProduct>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(cartProduct: List<CartProduct>)

}