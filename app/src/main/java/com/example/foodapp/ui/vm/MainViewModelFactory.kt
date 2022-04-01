package com.example.foodapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.domain.usecase.GetProductListUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val useCase: GetProductListUseCase)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(useCase) as T
    }


}