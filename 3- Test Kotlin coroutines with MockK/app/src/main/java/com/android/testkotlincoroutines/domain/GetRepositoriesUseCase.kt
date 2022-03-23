package com.android.testkotlincoroutines.domain

import com.android.testkotlincoroutines.data.GithubRepository
import com.android.testkotlincoroutines.data.RepositoryModel

class GetRepositoriesUseCase(private val apiRepository: GithubRepository) {
    suspend fun execute(username: String): List<RepositoryModel> {
        return this.apiRepository.fetchRepositories(username).map { RepositoryModel(it.id, it.name) }
    }
}