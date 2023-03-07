package com.example.clothyshop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}