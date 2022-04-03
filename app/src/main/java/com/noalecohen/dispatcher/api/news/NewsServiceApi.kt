package com.noalecohen.dispatcher.api.news

import com.noalecohen.dispatcher.BuildConfig
import com.noalecohen.dispatcher.model.response.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface NewsServiceApi {

    @Headers("${NewsServiceApiConstants.APIKEY_HEADER}:${BuildConfig.NEWS_API_KEY}")
    @GET(NewsServiceApiConstants.TOP_HEADLINES_ENDPOINT)
    fun fetchTopHeadlinesByCountry(
        @Query(NewsServiceApiConstants.COUNTRY) country: String?,
    ): Call<News>
}