package com.example.clothyshop.retrofit

import com.example.clothyshop.model.product.ProductsModelClass
import retrofit2.Response

interface   ApiHelper {
    suspend fun getProducts() :Response<ProductsModelClass>
}