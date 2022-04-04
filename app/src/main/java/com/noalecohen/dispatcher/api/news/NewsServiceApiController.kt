package com.noalecohen.dispatcher.api.news

import com.noalecohen.dispatcher.api.ApiController
import com.noalecohen.dispatcher.model.response.News
import retrofit2.Call

class NewsServiceApiController : ApiController<NewsServiceApi>(NewsServiceApi::class.java) {

    fun fetchTopHeadlinesByCountry(country: String): Call<News> {
        return api.fetchTopHeadlinesByCountry(country)
    }

    fun fetchTopHeadLinesByKeyword(keyword: String): Call<News> {
        return api.fetchTopHeadLinesByKeyword(keyword)
    }

}