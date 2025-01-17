package com.ujjwal.ecommerce.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
// CartDatabase: A Room database that holds the cart items and provides access to the CartDao
// Room Database Documentation: https://developer.android.com/training/data-storage/room
abstract class CartDatabase : RoomDatabase() {
	// Provides access to the CartDao for interacting with the cart items table
	abstract fun cartDao(): CartDao
}