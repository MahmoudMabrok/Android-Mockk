package com.android.testkotlincoroutines.data


interface GithubRepository {

    suspend fun fetchRepositories(username: String) : List<ApiRepositoryModel>

}