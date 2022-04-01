package com.example.foodapp.di

import android.content.Context
import com.example.foodapp.data.local.db.ProductDB
import com.example.foodapp.data.local.db.ProductsDao
import com.example.foodapp.data.network.api.Environment
import com.example.foodapp.data.network.api.RetrofitServices
import com.example.foodapp.data.repository.GetProductListRepositoryImpl
import com.example.foodapp.domain.repository.GetProductListRepository
import com.example.foodapp.ui.fragment.MainFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [DataModule::class, DomainBinds::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}

@Module
class DataModule(val context: Context){

    @Provides
    fun provideContext(): Context{
        return context
    }

    @Provides
    fun provideDao(context: Context): ProductsDao {
        return ProductDB.getInstance(context = context).productDao()
    }

    @Provides
    fun provideServices(): RetrofitServices {
        val retrofit  = Retrofit.Builder()
            .baseUrl(Environment.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitServices::class.java)
    }

}

@Module
interface DomainBinds{
    @Binds
    fun bindGetProductListRepositoryImplToInterface(
        repositoryImpl: GetProductListRepositoryImpl
    ): GetProductListRepository
}