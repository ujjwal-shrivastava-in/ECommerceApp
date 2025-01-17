package com.ujjwal.ecommerce.api

import com.ujjwal.ecommerce.model.Product
import retrofit2.Response
import retrofit2.http.GET

// ProductApiService Interface
interface ProductApiService {
	// GET request to fetch a list of products
	@GET("products") // Endpoint for fetching products
	suspend fun getProducts(): Response<List<Product>> // Suspended function that returns a Response containing a list of Product objects
}