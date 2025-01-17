package com.ujjwal.ecommerce.api

import com.ujjwal.ecommerce.model.Product
import javax.inject.Inject

// ProductRepository Class
class ProductRepository @Inject constructor(private val apiService: ProductApiService) {

	// fetchProducts Function
	suspend fun fetchProducts(): List<Product> {
		val response = apiService.getProducts()  // Make API call to fetch products
		if (response.isSuccessful) {
			return response.body() ?: throw Exception("No products found.")  // Return products if successful
		} else {
			throw Exception("Error: ${response.code()} ${response.message()}")  // Handle error response
		}
	}
}