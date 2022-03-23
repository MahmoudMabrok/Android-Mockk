package com.android.testkotlincoroutines.data

class GithubApiDatasource(private val githubApiService: GithubApiService) : GithubRepository {

    override suspend fun fetchRepositories(username: String) =
        this.githubApiService.getUserRepositories(username).execute().body() ?: throw NullPointerException("No body")

}