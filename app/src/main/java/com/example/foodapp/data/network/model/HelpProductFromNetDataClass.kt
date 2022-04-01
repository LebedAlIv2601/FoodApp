package com.example.foodapp.data.network.model

import com.google.gson.annotations.SerializedName

data class HelpProductFromNetDataClass(
    @SerializedName("menuItems")
    val menuItems: List<ProductDataNetModel>
)
