package com.ujjwal.ecommerce.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// CartViewModel: ViewModel responsible for handling cart-related data and interacting with the CartRepository
// ViewModel Documentation: https://developer.android.com/topic/libraries/architecture/viewmodel

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {
	// cartItems: LiveData that provides the current list of cart items
	val cartItems: LiveData<List<CartItem>> = repository.getCartItems()

	// addOrUpdateCartItem: Calls the repository to add or update a cart item
	fun addOrUpdateCartItem(cartItem: CartItem) {
		viewModelScope.launch {
			repository.addOrUpdateCartItem(cartItem)
		}
	}

	// removeCartItem: Calls the repository to remove a cart item from the database using productId
	fun removeCartItem(productId: Int) {
		viewModelScope.launch {
			repository.removeCartItem(productId)
		}
	}

	// updateQuantity: Calls the repository to update the quantity of a specific cart item
	fun updateQuantity(productId: Int, quantity: Int) {
		viewModelScope.launch {
			repository.updateQuantity(productId, quantity)
		}
	}
}