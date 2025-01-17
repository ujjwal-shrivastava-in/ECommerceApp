// CartDao.kt
package com.ujjwal.ecommerce.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
	// addOrUpdateCartItem
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addOrUpdateCartItem(cartItem: CartItem)
	// getCartItems
	@Query("SELECT * FROM cart_items")
	fun getCartItems(): LiveData<List<CartItem>>
	// removeCartItem
	@Query("DELETE FROM cart_items WHERE productId = :productId")
	suspend fun removeCartItem(productId: Int)
	// updateQuantity
	@Query("UPDATE cart_items SET quantity = :quantity WHERE productId = :productId")
	suspend fun updateQuantity(productId: Int, quantity: Int)
	// clearCart
	@Query("DELETE FROM cart_items")
	suspend fun clearCart()
}