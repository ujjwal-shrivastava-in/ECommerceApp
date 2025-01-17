package com.ujjwal.ecommerce.database

import javax.inject.Inject

// CartRepository: Repository class responsible for interacting with the CartDao to manage cart data
// Repository Pattern Documentation: https://developer.android.com/guide/architecture/repository

class CartRepository @Inject constructor(private val cartDao: CartDao) {
	// getCartItems: Fetches the list of cart items from the database
	fun getCartItems() = cartDao.getCartItems()

	// addOrUpdateCartItem: Adds a new cart item or updates an existing one in the database
	suspend fun addOrUpdateCartItem(cartItem: CartItem) = cartDao.addOrUpdateCartItem(cartItem)

	// removeCartItem: Removes a cart item from the database using the productId
	suspend fun removeCartItem(productId: Int) = cartDao.removeCartItem(productId)

	// updateQuantity: Updates the quantity of a specific cart item in the database
	suspend fun updateQuantity(productId: Int, quantity: Int) = cartDao.updateQuantity(productId, quantity)
}