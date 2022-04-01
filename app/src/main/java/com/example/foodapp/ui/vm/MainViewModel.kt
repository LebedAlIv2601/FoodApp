package com.example.foodapp.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.foodapp.domain.usecase.GetProductListUseCase
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val useCase: GetProductListUseCase): ViewModel() {

    val productList = useCase()

}