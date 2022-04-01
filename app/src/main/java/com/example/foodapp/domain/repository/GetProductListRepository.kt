package com.example.foodapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.foodapp.domain.model.ProductDomainModel
import com.example.foodapp.utils.Resource

interface GetProductListRepository {

    fun getProductList(): LiveData<Resource<List<ProductDomainModel>>>

}