package com.ujjwal.ecommerce.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
// CartDatabase
abstract class CartDatabase : RoomDatabase() {
	abstract fun cartDao(): CartDao
}