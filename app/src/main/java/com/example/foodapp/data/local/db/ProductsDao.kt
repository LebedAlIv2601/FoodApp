package com.example.foodapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.local.model.ProductDataLocalModel

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    fun getProducts(): LiveData<List<ProductDataLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productList: List<ProductDataLocalModel>)
}