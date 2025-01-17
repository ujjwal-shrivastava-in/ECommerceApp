// CartViewModel.kt
package com.ujjwal.ecommerce.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {
	val cartItems: LiveData<List<CartItem>> = repository.getCartItems()
	// addOrUpdateCartItem
	fun addOrUpdateCartItem(cartItem: CartItem) {
		viewModelScope.launch {
			repository.addOrUpdateCartItem(cartItem)
		}
	}
	// removeCartItem
	fun removeCartItem(productId: Int) {
		viewModelScope.launch {
			repository.removeCartItem(productId)
		}
	}
	// updateQuantity
	fun updateQuantity(productId: Int, quantity: Int) {
		viewModelScope.launch {
			repository.updateQuantity(productId, quantity)
		}
	}
}