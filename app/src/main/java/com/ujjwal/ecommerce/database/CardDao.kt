// CartDao.kt
package com.ujjwal.ecommerce.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
	// addOrUpdateCartItem: Inserts a new cart item or updates the existing one if the product already exists in the cart
	// Reference for Room Database: https://developer.android.com/training/data-storage/room
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addOrUpdateCartItem(cartItem: CartItem)

	// getCartItems: Retrieves all items in the cart as a LiveData object
	// LiveData: https://developer.android.com/topic/libraries/architecture/livedata
	@Query("SELECT * FROM cart_items")
	fun getCartItems(): LiveData<List<CartItem>>

	// removeCartItem: Removes a cart item based on the product ID
	// Room Database Query Reference: https://developer.android.com/reference/android/arch/persistence/room/Query
	@Query("DELETE FROM cart_items WHERE productId = :productId")
	suspend fun removeCartItem(productId: Int)

	// updateQuantity: Updates the quantity of a cart item based on the product ID
	@Query("UPDATE cart_items SET quantity = :quantity WHERE productId = :productId")
	suspend fun updateQuantity(productId: Int, quantity: Int)

	// clearCart: Deletes all items from the cart
	@Query("DELETE FROM cart_items")
	suspend fun clearCart()
}