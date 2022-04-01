package com.example.foodapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.foodapp.data.local.db.ProductsDao
import com.example.foodapp.utils.Resource
import com.example.foodapp.data.network.api.ApiHelper
import com.example.foodapp.data.toDomain
import com.example.foodapp.data.toLocal
import com.example.foodapp.domain.model.ProductDomainModel
import com.example.foodapp.domain.repository.GetProductListRepository
import com.example.foodapp.utils.resultLiveData
import javax.inject.Inject

class GetProductListRepositoryImpl @Inject constructor(private val dao: ProductsDao,
                                                       private val apiHelper: ApiHelper)
    : GetProductListRepository {

    private val productsList = resultLiveData(
        databaseQuery = {dao.getProducts()},
        networkCall = {apiHelper.getProductListFromNet()},
        saveCallResult = {dao.insertAll(it.map{item -> item.toLocal()} )}
    ). map {
        Resource<List<ProductDomainModel>>(
            status  = it.status,
            data = it.data?.map { item -> item.toDomain() },
            message = it.message
        )
    }

    override fun getProductList(): LiveData<Resource<List<ProductDomainModel>>>{
        return productsList
    }
}