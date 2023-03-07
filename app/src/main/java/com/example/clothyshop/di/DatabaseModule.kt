package com.example.clothyshop.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.clothyshop.db.ShopDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context : Context) : ShopDB{
        return Room.databaseBuilder(context, ShopDB::class.java, "ShopDB").fallbackToDestructiveMigration().build()
    }
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}