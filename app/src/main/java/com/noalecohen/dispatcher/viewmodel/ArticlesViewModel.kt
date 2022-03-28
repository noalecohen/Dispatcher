package com.noalecohen.dispatcher.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.model.response.News
import com.noalecohen.dispatcher.repository.ArticlesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()

    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()

    fun fetchTopHeadlinesByCountry(country: String) {
        articlesRepository.fetchTopHeadlinesByCountry(country).enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    articlesLiveData.postValue(apiResponse?.articles)
                } else {
                    Log.d("Request Error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Network Error", t.localizedMessage)
            }
        })
    }

}