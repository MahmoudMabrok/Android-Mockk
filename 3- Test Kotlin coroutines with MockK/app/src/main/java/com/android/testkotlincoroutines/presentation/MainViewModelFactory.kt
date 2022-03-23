package com.android.testkotlincoroutines.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testkotlincoroutines.domain.GetRepositoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher

class MainViewModelFactory constructor(
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(mainDispatcher, ioDispatcher, getRepositoriesUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}