package com.noalecohen.dispatcher.repository

import com.noalecohen.dispatcher.api.news.NewsServiceApiController
import com.noalecohen.dispatcher.model.response.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticlesRepository {
    private val apiController = NewsServiceApiController()

    suspend fun fetchTopHeadlinesByCountry(country: String): List<Article>? {

        return withContext(Dispatchers.IO) {
            apiController.fetchTopHeadlinesByCountry(country).body()?.articles
        }

    }

    suspend fun fetchFilterResults(keyword: String): List<Article>? {

        return withContext(Dispatchers.IO) {
            apiController.fetchFilterResults(keyword).body()?.articles
        }

    }

}