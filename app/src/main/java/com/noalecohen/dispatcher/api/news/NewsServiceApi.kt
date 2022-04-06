package com.noalecohen.dispatcher.api.news

import com.noalecohen.dispatcher.model.response.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsServiceApi {

    @GET(NewsServiceApiConstants.TOP_HEADLINES_ENDPOINT)
    suspend fun fetchTopHeadlinesByCountry(
        @Query(NewsServiceApiConstants.COUNTRY) country: String?,
    ): Response<News>


    @GET(NewsServiceApiConstants.EVERYTHING_ENDPOINT)
    suspend fun fetchFilterResults(
        @Query(NewsServiceApiConstants.KEYWORD) keyword: String
    ): Response<News>
}