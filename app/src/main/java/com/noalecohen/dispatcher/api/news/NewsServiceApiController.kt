package com.noalecohen.dispatcher.api.news

import com.noalecohen.dispatcher.api.ApiController
import com.noalecohen.dispatcher.model.response.News
import retrofit2.Response

class NewsServiceApiController : ApiController<NewsServiceApi>(NewsServiceApi::class.java) {

    suspend fun fetchTopHeadlinesByCountry(country: String): Response<News> {
        return api.fetchTopHeadlinesByCountry(country)
    }

    suspend fun fetchFilterResults(keyword: String): Response<News> {
        return api.fetchFilterResults(keyword)
    }

}