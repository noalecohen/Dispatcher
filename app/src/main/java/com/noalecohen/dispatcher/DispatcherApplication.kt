package com.noalecohen.dispatcher

import android.app.Application
import com.noalecohen.dispatcher.networking.NetworkManager

class DispatcherApplication : Application() {
    private var networkManager: NetworkManager = NetworkManager

    init {
        networkManager.initRetrofit(BASE_URL)
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}