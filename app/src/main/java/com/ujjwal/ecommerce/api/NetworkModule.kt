package com.ujjwal.ecommerce.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl("https://fakestoreapi.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	@Provides
	@Singleton
	fun provideProductApiService(retrofit: Retrofit): ProductApiService {
		return retrofit.create(ProductApiService::class.java)
	}
}