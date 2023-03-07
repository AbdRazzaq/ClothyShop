package com.example.clothyshop.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.clothyshop.model.product.Product
import com.example.clothyshop.model.productdetails.ProductDetailsModel
import com.example.clothyshop.model.profile.ProfileModelClass
import com.example.clothyshop.model.StringListConvertor
import com.example.clothyshop.model.cart.CartProduct

@Database(entities = [Product::class, ProfileModelClass::class,ProductDetailsModel::class, CartProduct::class], version = 4,exportSchema = false)
@TypeConverters(StringListConvertor::class)
abstract class ShopDB : RoomDatabase(){

    abstract fun getShopDAO() : ShopDAO

}