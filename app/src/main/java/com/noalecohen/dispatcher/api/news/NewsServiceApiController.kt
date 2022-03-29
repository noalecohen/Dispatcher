package com.noalecohen.dispatcher.api.news

import com.noalecohen.dispatcher.BuildConfig
import com.noalecohen.dispatcher.api.ApiController
import com.noalecohen.dispatcher.model.response.News
import retrofit2.Call

class NewsServiceApiController() : ApiController() {

    fun fetchTopHeadlinesByCountry(country: String): Call<News> {
        return api!!.fetchTopHeadlinesByCountry(country, BuildConfig.NEWS_API_KEY)
    }

}