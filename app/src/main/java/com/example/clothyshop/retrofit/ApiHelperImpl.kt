package com.example.clothyshop.retrofit

import com.example.clothyshop.model.product.ProductsModelClass
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val shopApi: ShopApi): ApiHelper {
    override suspend fun getProducts(): Response<ProductsModelClass>  = shopApi.getProducts()
}