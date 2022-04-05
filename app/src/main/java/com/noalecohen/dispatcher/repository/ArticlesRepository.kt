package com.noalecohen.dispatcher.repository

import com.noalecohen.dispatcher.api.news.NewsServiceApiController
import com.noalecohen.dispatcher.model.response.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//sealed class Result<out T> {
//    data class Success<out T>(val data: T) : Result<T>()
//    data class Error(val exception: Exception) : Result<Nothing>()
//}

class ArticlesRepository {
    private val apiController = NewsServiceApiController()

    suspend fun fetchTopHeadlinesByCountry(country: String): List<Article>? {

        return withContext(Dispatchers.IO) {

            apiController.fetchTopHeadlinesByCountry(country).body()?.articles

//            try {
//                val response = apiController.fetchTopHeadlinesByCountry(country).execute()
//                response.body()?.articles
//            } catch (e: Exception) {
//                emptyList()
//            }


//            apiController.fetchTopHeadlinesByCountry(country).enqueue(object : Callback<News> {
//
//                override fun onResponse(call: Call<News>, response: Response<News>) {
//                    if (response.isSuccessful) {
//                        response.body()?.let { return Result.Success(it.articles) }
//                    } else {
//                        val errorResponse = Gson().fromJson(
//                            response.errorBody()?.charStream(),
//                            ErrorResponse::class.java
//                        )
//                        callback(emptyList(), errorResponse.message)
//                    }
//                }
//
//                override fun onFailure(call: Call<News>, t: Throwable) {
//                    callback(emptyList(), t.localizedMessage)
//                }
//            })
        }
    }


    suspend fun fetchFilterResults(keyword: String): List<Article>? {

        return withContext(Dispatchers.IO) {
            apiController.fetchFilterResults(keyword).body()?.articles
        }
//        apiController.fetchFilterResults(keyword)
//            .enqueue(object : Callback<News> {
//                override fun onResponse(call: Call<News>, response: Response<News>) {
//                    if (response.isSuccessful) {
//                        response.body()?.let { callback(it.articles, null) }
//                    } else {
//                        val errorResponse = Gson().fromJson(
//                            response.errorBody()?.charStream(),
//                            ErrorResponse::class.java
//                        )
//                        callback(emptyList(), errorResponse.message)
//                    }
//                }
//
//                override fun onFailure(call: Call<News>, t: Throwable) {
//                    callback(emptyList(), t.localizedMessage)
//                }
//
//            })
    }

}