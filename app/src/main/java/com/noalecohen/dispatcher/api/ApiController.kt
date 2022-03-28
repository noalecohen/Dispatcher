package com.noalecohen.dispatcher.api

import com.noalecohen.dispatcher.api.news.NewsServiceApi
import com.noalecohen.dispatcher.networking.NetworkManager

open class ApiController {
    protected var api: NewsServiceApi? = NetworkManager.retrofit?.create(NewsServiceApi::class.java)

}