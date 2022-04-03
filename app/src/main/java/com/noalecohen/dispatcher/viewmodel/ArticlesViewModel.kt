package com.noalecohen.dispatcher.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.model.response.ErrorResponse
import com.noalecohen.dispatcher.model.response.News
import com.noalecohen.dispatcher.repository.ArticlesRepository
import com.noalecohen.dispatcher.viewstate.RequestState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()

    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val requestStateLiveData: MutableLiveData<RequestState> = MutableLiveData(RequestState.Idle)

    fun fetchTopHeadlinesByCountry(country: String) {
        articlesRepository.fetchTopHeadlinesByCountry(country).enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    articlesLiveData.postValue(apiResponse?.articles)
                    requestStateLiveData.postValue(RequestState.Success)
                } else {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        ErrorResponse::class.java
                    )
                    requestStateLiveData.postValue(RequestState.Error(errorResponse.message))
                    Log.d("Request Error", errorResponse.message)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                requestStateLiveData.postValue(RequestState.Error(t.toString()))
                Log.d("Network Error", t.localizedMessage)
            }
        })
    }

}