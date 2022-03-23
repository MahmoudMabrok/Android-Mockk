package com.android.testkotlincoroutines.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.testkotlincoroutines.R
import com.android.testkotlincoroutines.data.GithubApiDatasource
import com.android.testkotlincoroutines.data.GithubApiService
import com.android.testkotlincoroutines.data.RepositoryModel
import com.android.testkotlincoroutines.domain.GetRepositoriesUseCase
import com.android.testkotlincoroutines.utils.LiveDataResult
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    private val githubService: GithubApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GithubApiService::class.java)
    }

    private val mainViewModelFactory: MainViewModelFactory = MainViewModelFactory(
        Dispatchers.Main,
        Dispatchers.IO,
        GetRepositoriesUseCase(GithubApiDatasource(this.githubService))
    )

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    private val dataObserver = Observer<LiveDataResult<List<RepositoryModel>>> {liveData->
        findViewById<AppCompatButton>(R.id.progressBar).isVisible = false
        // User data
        when (liveData?.status) {
            LiveDataResult.STATUS.ERROR -> {

            }
            LiveDataResult.STATUS.SUCCESS -> {
                for(value in liveData.data!!){
                    val text = findViewById<AppCompatTextView>(R.id.text).text.toString()
                    findViewById<AppCompatTextView>(R.id.text).text = text + value.repositoryName+"\n"
                }
            }
            LiveDataResult.STATUS.LOADING -> {
                findViewById<AppCompatButton>(R.id.progressBar).isVisible = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mainViewModel.repositoriesLiveData.observe(this, this.dataObserver)

        findViewById<AppCompatButton>(R.id.process).setOnClickListener {
            this.mainViewModel.fetchRepositories("mirtizakh")
        }

    }
}