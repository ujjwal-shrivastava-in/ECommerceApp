package com.ujjwal.ecommerce.api

import com.ujjwal.ecommerce.model.Product
import retrofit2.Response
import retrofit2.http.GET
// ProductApiService
interface ProductApiService {
	@GET("products")
	suspend fun getProducts(): Response<List<Product>>
}