package com.noalecohen.dispatcher.repository

import com.noalecohen.dispatcher.api.news.NewsServiceApiController
import com.noalecohen.dispatcher.model.response.News
import retrofit2.Call

class ArticlesRepository {
    private val apiController = NewsServiceApiController()

    fun fetchTopHeadlinesByCountry(country: String): Call<News> {
        return apiController.fetchTopHeadlinesByCountry(country)
    }

}