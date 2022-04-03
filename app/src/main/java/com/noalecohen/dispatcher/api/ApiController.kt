package com.noalecohen.dispatcher.api

import com.noalecohen.dispatcher.networking.NetworkManager

abstract class ApiController<T>(clazz: Class<T>) {

    protected var api: T = NetworkManager.retrofit.create(clazz)
}