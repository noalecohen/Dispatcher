package com.noalecohen.dispatcher.repository

import com.noalecohen.dispatcher.api.news.NewsServiceApiController
import com.noalecohen.dispatcher.model.response.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticlesRepository {
    private val apiController = NewsServiceApiController()

    suspend fun fetchTopHeadlinesByCountry(country: String): Flow<List<Article>> {
        return flow {
            apiController.fetchTopHeadlinesByCountry(country).body()?.let { emit(it.articles) }
        }
    }


    suspend fun fetchFilterResults(keyword: String): Flow<List<Article>> {
        return flow {
            apiController.fetchFilterResults(keyword).body()?.let { emit(it.articles) }
        }
    }

}