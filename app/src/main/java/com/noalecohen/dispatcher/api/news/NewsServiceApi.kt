package com.noalecohen.dispatcher.api.news

import com.noalecohen.dispatcher.model.response.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsServiceApi {

    @GET(NewsServiceApiConstants.TOP_HEADLINES_ENDPOINT)
    fun fetchTopHeadlinesByCountry(
        @Query(NewsServiceApiConstants.COUNTRY) country: String?,
    ): Call<News>


    @GET(NewsServiceApiConstants.EVERYTHING_ENDPOINT)
    fun fetchFilterResults(
        @Query(NewsServiceApiConstants.KEYWORD) keyword: String
    ): Call<News>
}