package com.ujjwal.ecommerce.api

import com.ujjwal.ecommerce.model.Product
import javax.inject.Inject
// ProductRepository
class ProductRepository @Inject constructor(private val apiService: ProductApiService) {
	// fetchProducts
	suspend fun fetchProducts(): List<Product> {
		val response = apiService.getProducts()
		if (response.isSuccessful) {
			return response.body() ?: throw Exception("No products found.")
		} else {
			throw Exception("Error: ${response.code()} ${response.message()}")
		}
	}
}