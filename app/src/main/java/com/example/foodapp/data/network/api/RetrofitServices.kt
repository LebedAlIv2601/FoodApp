package com.example.foodapp.data.network.api

import com.example.foodapp.data.network.model.HelpProductFromNetDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices  {

    @GET("search")
    suspend fun getProductList(@Query("query") query: String,
                               @Query("number") number: Int,
                               @Query("apiKey") apiKey: String):
            Response<HelpProductFromNetDataClass>
}