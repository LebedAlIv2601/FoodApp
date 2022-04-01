package com.example.foodapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.foodapp.domain.model.ProductDomainModel
import com.example.foodapp.domain.repository.GetProductListRepository
import com.example.foodapp.utils.Resource
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: GetProductListRepository) {

    operator fun invoke(): LiveData<Resource<List<ProductDomainModel>>>{
        return repository.getProductList()
    }
}