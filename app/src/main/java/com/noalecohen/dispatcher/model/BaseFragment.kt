package com.noalecohen.dispatcher.model

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    var articleList = ArrayList<Article>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticleList()
    }

    private fun initArticleList() {
        val article1 = Article(
            null,
            null,
            null,
            null,
        )
        val article2 = Article(
            "Title 2",
            null,
            "Ben Cohen",
            "Body 2 Text"
        )
        val article3 = Article(
            "Title 3",
            null,
            "Omri Ovadia",
            "Body"
        )
        articleList.addAll(listOf(article1, article2, article3))
    }
}
