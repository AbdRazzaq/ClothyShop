package com.example.clothyshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothyshop.model.product.ProductsModelClass
import com.example.clothyshop.model.profile.ProfileModelClass
import com.example.clothyshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllProducts()
            repository.getUser()
        }
    }

    val products: LiveData<ProductsModelClass>
        get() = repository.products

    val user: LiveData<ProfileModelClass>
        get() = repository.userData
}

