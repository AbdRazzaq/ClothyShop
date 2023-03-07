package com.example.clothyshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothyshop.model.cart.CartModelClass
import com.example.clothyshop.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel(){
    init {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.getCart()
        }
    }

    val cartOfUser: LiveData<CartModelClass>
        get() = cartRepository.cart
}