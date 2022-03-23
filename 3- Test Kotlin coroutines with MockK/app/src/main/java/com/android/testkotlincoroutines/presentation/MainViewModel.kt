package com.android.testkotlincoroutines.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.testkotlincoroutines.data.RepositoryModel
import com.android.testkotlincoroutines.domain.GetRepositoriesUseCase
import com.android.testkotlincoroutines.utils.LiveDataResult
import kotlinx.coroutines.*

class MainViewModel(
    mainDispatcher : CoroutineDispatcher,
    ioDispatcher : CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
    ) : ViewModel() {

        private val job = SupervisorJob()

        val repositoriesLiveData = MutableLiveData<LiveDataResult<List<RepositoryModel>>>()

        private val uiScope = CoroutineScope(mainDispatcher + job)

        val ioScope = CoroutineScope(ioDispatcher + job)

        fun fetchRepositories(user: String) {
            uiScope.launch {
                repositoriesLiveData.value = LiveDataResult.loading()

                try {
                    val data = ioScope.async {
                        return@async getRepositoriesUseCase.execute(user)
                    }.await()

                    repositoriesLiveData.value = LiveDataResult.success(data)
                } catch (e: Exception) {
                    repositoriesLiveData.value = LiveDataResult.error(e)
                }

            }

        }

        override fun onCleared() {
            super.onCleared()
            this.job.cancel()
        }
}
