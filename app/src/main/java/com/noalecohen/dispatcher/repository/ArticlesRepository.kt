package com.noalecohen.dispatcher.repository

import com.noalecohen.dispatcher.api.news.NewsServiceApiController
import com.noalecohen.dispatcher.model.response.Article

class ArticlesRepository {
    private val apiController = NewsServiceApiController()

    suspend fun fetchTopHeadlinesByCountry(country: String): List<Article>? {

        return apiController.fetchTopHeadlinesByCountry(country).body()?.articles

    }

    suspend fun fetchFilterResults(keyword: String): List<Article>? {

        return apiController.fetchFilterResults(keyword).body()?.articles

    }

}