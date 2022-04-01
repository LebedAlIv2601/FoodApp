package com.example.foodapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class ProductDataLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val image: String,
    val restaurant: String
)

