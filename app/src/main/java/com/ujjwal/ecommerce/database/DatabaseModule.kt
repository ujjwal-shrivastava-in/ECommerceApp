package com.ujjwal.ecommerce.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// DatabaseModule: Dagger module for providing the database instance and DAO (Data Access Object) for CartItem.
// Dagger Documentation: https://dagger.dev/hilt/

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	// provideAppDatabase: Provides a singleton instance of the Room database.
	@Singleton
	@Provides
	fun provideAppDatabase(@ApplicationContext context: Context): CartDatabase {
		return Room.databaseBuilder(
			context.applicationContext,
			CartDatabase::class.java,
			"ecommerce_database" // Database name
		).build()
	}

	// provideCartDao: Provides the CartDao for database operations related to cart items.
	@Singleton
	@Provides
	fun provideCartDao(cartDatabase: CartDatabase): CartDao {
		return cartDatabase.cartDao()
	}
}