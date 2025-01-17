package com.ujjwal.ecommerce.database

import javax.inject.Inject

// CartRepository
class CartRepository @Inject constructor(private val cartDao: CartDao) {
	// getCartItems
	fun getCartItems() = cartDao.getCartItems()
	// addOrUpdateCartItem
	suspend fun addOrUpdateCartItem(cartItem: CartItem) = cartDao.addOrUpdateCartItem(cartItem)
	// removeCartItems
	suspend fun removeCartItem(productId: Int) = cartDao.removeCartItem(productId)
	// updateQuantity
	suspend fun updateQuantity(productId: Int, quantity: Int) = cartDao.updateQuantity(productId, quantity)
}