package com.example.foodapp.app

import android.app.Application
import android.content.Context
import com.example.foodapp.di.AppComponent
import com.example.foodapp.di.DaggerAppComponent
import com.example.foodapp.di.DataModule

class FoodApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(context =this)).build()
    }

}

val Context.appComponent: AppComponent
    get() = when(this){
        is FoodApplication -> appComponent
        else -> applicationContext.appComponent
    }