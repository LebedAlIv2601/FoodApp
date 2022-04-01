package com.example.foodapp.data

import com.example.foodapp.data.local.model.ProductDataLocalModel
import com.example.foodapp.data.network.model.ProductDataNetModel
import com.example.foodapp.domain.model.ProductDomainModel

fun ProductDataLocalModel.toDomain(): ProductDomainModel{
    return ProductDomainModel(
        title = title,
        image = image,
        restaurant = restaurant
    )
}

fun ProductDataNetModel.toLocal(): ProductDataLocalModel{
    return ProductDataLocalModel(
        id = 0,
        title = title,
        image = image,
        restaurant = restaurant
    )
}
