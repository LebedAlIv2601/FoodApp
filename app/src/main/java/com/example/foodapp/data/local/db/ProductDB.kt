package com.example.foodapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.data.local.model.ProductDataLocalModel
import com.example.foodapp.data.network.model.ProductDataNetModel

@Database(entities = [ProductDataLocalModel::class], version = 1)
abstract class ProductDB : RoomDatabase() {
    abstract fun productDao(): ProductsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDB? = null

        fun getInstance(context: Context): ProductDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        ProductDB::class.java, "products_db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}