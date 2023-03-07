package com.example.clothyshop.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.clothyshop.db.ShopDB
import com.example.clothyshop.model.product.ProductsModelClass
import com.example.clothyshop.model.productdetails.ProductDetailsModel
import com.example.clothyshop.model.profile.ProfileModelClass
import com.example.clothyshop.retrofit.ShopApi
import com.example.clothyshop.utils.NetworkUtils
import javax.inject.Inject

class ProductRepository @Inject constructor(private val shopApi: ShopApi,
                                            private val shopDB: ShopDB,
                                            private val applicationContext: Context) {

    private val productsLiveData = MutableLiveData<ProductsModelClass>()
    private val userLiveData = MutableLiveData<ProfileModelClass>()
    private val productDetailsData = MutableLiveData<ProductDetailsModel>()

    val products: LiveData<ProductsModelClass>
        get() = productsLiveData
    val userData: LiveData<ProfileModelClass>
        get() = userLiveData
    val details: LiveData<ProductDetailsModel>
        get() = productDetailsData

    suspend fun getAllProducts(){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = shopApi.getProducts()
            if(result.body() != null){
                shopDB.getShopDAO().addProducts(result.body()!!.products)
                productsLiveData.postValue(result.body())
            }
        } else{
            val products = shopDB.getShopDAO().getProducts()
            val quoteList = ProductsModelClass(1,products, 1, 1)
            productsLiveData.postValue(quoteList)
        }
    }

    suspend fun getUser(){
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = shopApi.getUser()
            if(result.body() != null){
                shopDB.getShopDAO().addUser(result.body()!!)
                userLiveData.postValue(result.body())
            }
        } else{
            val user = shopDB.getShopDAO().getUser()
            userLiveData.postValue(user)
        }
    }
    suspend fun getProductDetails(id : Int){
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = shopApi.getProductDetails(id)
            if(result.body() != null){
                shopDB.getShopDAO().addProductsDetails(result.body()!!)
                productDetailsData.postValue(result.body())
            }
        } else{
            val detailsData = shopDB.getShopDAO().getProductDetails()
            productDetailsData.postValue(detailsData)
        }
    }

    //suspend fun getAllProducts() = apiHelper.getProducts()

}