package com.noalecohen.dispatcher.model

import java.io.Serializable

data class Article(
    var title: String?,
    var imageUrl: String?,
    var author: String?,
    var body: String?
) : Serializable