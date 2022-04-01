package com.example.foodapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ProductDataNetModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("restaurantChain")
    val restaurant: String
)
