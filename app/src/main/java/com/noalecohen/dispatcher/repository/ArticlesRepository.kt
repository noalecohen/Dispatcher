package com.noalecohen.dispatcher.repository

import com.google.gson.Gson
import com.noalecohen.dispatcher.api.news.NewsServiceApiController
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.model.response.ErrorResponse
import com.noalecohen.dispatcher.model.response.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesRepository {
    private val apiController = NewsServiceApiController()

    fun fetchTopHeadlinesByCountry(country: String, callback: (List<Article>, String?) -> Unit) {

        apiController.fetchTopHeadlinesByCountry(country).enqueue(object : Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback(it.articles, null) }
                } else {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        ErrorResponse::class.java
                    )
                    callback(emptyList(), errorResponse.message)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                callback(emptyList(), t.localizedMessage)
            }
        })
    }


    fun fetchTopHeadLinesByKeyword(keyword: String, callback: (List<Article>, String?) -> Unit) {

        apiController.fetchTopHeadLinesByKeyword(keyword)
            .enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback(it.articles, null) }
                    } else {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.charStream(),
                            ErrorResponse::class.java
                        )
                        callback(emptyList(), errorResponse.message)
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    callback(emptyList(), t.localizedMessage)
                }

            })
    }

}