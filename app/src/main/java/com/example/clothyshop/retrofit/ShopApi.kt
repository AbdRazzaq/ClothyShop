package com.example.clothyshop.retrofit


import com.example.clothyshop.model.cart.CartModelClass
import com.example.clothyshop.model.product.ProductsModelClass
import com.example.clothyshop.model.productdetails.ProductDetailsModel
import com.example.clothyshop.model.profile.ProfileModelClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopApi {

    @GET("products")
    suspend fun getProducts() : Response<ProductsModelClass> //ProductItem

    @GET("users/2")
    suspend fun getUser() : Response<ProfileModelClass>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int) : Response<ProductDetailsModel>

    @GET("carts/1")
    suspend fun getCart() : Response<CartModelClass>

}
