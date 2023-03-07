package com.example.clothyshop.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.clothyshop.db.ShopDB
import com.example.clothyshop.model.cart.CartModelClass
import com.example.clothyshop.model.product.ProductsModelClass
import com.example.clothyshop.retrofit.ShopApi
import com.example.clothyshop.utils.NetworkUtils
import javax.inject.Inject

class CartRepository@Inject constructor(private val shopApi: ShopApi,
                                        private val shopDB: ShopDB,
                                        private val applicationContext: Context) {
    private val cartsLiveData = MutableLiveData<CartModelClass>()

    val cart: LiveData<CartModelClass>
        get() = cartsLiveData

    suspend fun getCart(){
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = shopApi.getCart()
            if(result.body() != null){
                shopDB.getShopDAO().addCart(result.body()!!.products)
                cartsLiveData.postValue(result.body())
            }
        } else{
            val cart = shopDB.getShopDAO().getCart()
            val cartList = CartModelClass(1,1,cart, 1, 1,1,1)
            cartsLiveData.postValue(cartList)
        }
    }
}