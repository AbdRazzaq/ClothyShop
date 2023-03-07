package com.example.clothyshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothyshop.model.productdetails.ProductDetailsModel
import com.example.clothyshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    fun getProductDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductDetails(id)
        }
    }

    val productDetails: LiveData<ProductDetailsModel>
        get() = repository.details
}