package com.ujjwal.ecommerce.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module // Indicates this is a Dagger module
@InstallIn(SingletonComponent::class) // Specifies the scope of the module, making it available throughout the app's lifecycle
object NetworkModule {

	// Provides a singleton instance of Retrofit
	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl("https://fakestoreapi.com/") // Set the base URL for API requests
			.addConverterFactory(GsonConverterFactory.create()) // Add Gson converter for parsing JSON responses
			.build() // Build the Retrofit instance
	}

	// Provides a singleton instance of the ProductApiService
	@Provides
	@Singleton
	fun provideProductApiService(retrofit: Retrofit): ProductApiService {
		return retrofit.create(ProductApiService::class.java) // Create an implementation of the ProductApiService using Retrofit
	}
}