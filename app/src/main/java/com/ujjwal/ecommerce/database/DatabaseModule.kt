package com.ujjwal.ecommerce.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// DatabaseModule
object DatabaseModule {
	@Singleton
	@Provides
	// provideAppDatabase
	fun provideAppDatabase(@ApplicationContext context: Context): CartDatabase {
		return Room.databaseBuilder(
			context.applicationContext,
			CartDatabase::class.java,
			"ecommerce_database"
		).build()
	}
	@Singleton
	@Provides
	fun provideCartDao(cartDatabase: CartDatabase): CartDao {
		return cartDatabase.cartDao()
	}
}