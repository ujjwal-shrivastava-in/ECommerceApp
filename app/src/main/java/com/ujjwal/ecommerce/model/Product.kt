package com.ujjwal.ecommerce.model

// Product: Represents a product in the eCommerce application.
// Contains basic details like id, title, price, and image URL.
data class Product(
	val id: Int, // Product ID
	val title: String, // Product name or title
	val price: Double, // Product price
	val image: String // URL to the product's image
)