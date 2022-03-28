package com.noalecohen.dispatcher.repository

import com.noalecohen.dispatcher.model.Article

class ArticlesRepository {

    //fake data
    fun fetchArticles(): List<Article> {
        return mutableListOf(
            Article("title 1", null, "author 1", "body 1"),
            Article("title 2", null, "author 2", "body 2"),
            Article("title 3", null, "author 3", "body 3")
        )
    }

}