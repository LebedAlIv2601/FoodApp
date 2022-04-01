package com.example.foodapp.data.network.api

import com.example.foodapp.data.network.model.ProductDataNetModel
import com.example.foodapp.utils.BaseDataSource
import com.example.foodapp.utils.Resource
import javax.inject.Inject

class ApiHelper @Inject constructor(private val services: RetrofitServices) : BaseDataSource() {


    suspend fun getProductListFromNet(): Resource<List<ProductDataNetModel>>{

        val pizzaList = getResult {services.getProductList("pizza", Environment.DEFAULT_PAGE_SIZE, Token.TOKEN)}
        val drinkList = getResult {services.getProductList("soda", Environment.DEFAULT_PAGE_SIZE, Token.TOKEN)}
        val dessertList = getResult {services.getProductList("dessert", Environment.DEFAULT_PAGE_SIZE, Token.TOKEN)}
        val snacksList = getResult {services.getProductList("roll", Environment.DEFAULT_PAGE_SIZE, Token.TOKEN)}
        val sundryList = getResult {services.getProductList("sauce", Environment.DEFAULT_PAGE_SIZE, Token.TOKEN)}

        val allList = mutableListOf<ProductDataNetModel>()
        pizzaList.data?.menuItems?.let { allList.addAll(it) }
        drinkList.data?.menuItems?.let { allList.addAll(it) }
        dessertList.data?.menuItems?.let { allList.addAll(it) }
        snacksList.data?.menuItems?.let { allList.addAll(it) }
        sundryList.data?.menuItems?.let { allList.addAll(it) }

        return Resource(sundryList.status, allList, sundryList.message)
    }

}