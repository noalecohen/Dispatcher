package com.noalecohen.dispatcher.networking

import com.noalecohen.dispatcher.BuildConfig
import com.noalecohen.dispatcher.api.news.NewsServiceApiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManager {

    private var httpClient: OkHttpClient =
        OkHttpClient.Builder().addInterceptor {
            it.proceed(
                it.request().newBuilder()
                    .addHeader(NewsServiceApiConstants.APIKEY_HEADER, BuildConfig.NEWS_API_KEY)
                    .build()
            )
        }.build()

    val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

}

