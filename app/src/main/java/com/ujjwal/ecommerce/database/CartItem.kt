// CartItem.kt
package com.ujjwal.ecommerce.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
// CartItem
data class CartItem(
	@PrimaryKey val productId: Int,
	val productName: String,
	val productImage: String,
	val productPrice: Double,
	val quantity: Int
)