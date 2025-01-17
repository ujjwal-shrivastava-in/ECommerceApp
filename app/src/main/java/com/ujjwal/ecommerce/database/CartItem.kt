package com.ujjwal.ecommerce.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
// CartItem: Data class representing a product item in the cart with product details and quantity
// Room Entity Documentation: https://developer.android.com/reference/android/arch/persistence/room/Entity
data class CartItem(
	@PrimaryKey val productId: Int, // Unique identifier for the product in the cart
	val productName: String, // Name of the product
	val productImage: String, // URL or path to the product's image
	val productPrice: Double, // Price of the product
	val quantity: Int // Quantity of the product in the cart
)