package model

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment() : Fragment() {
    lateinit var articleList: Array<Article>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArticleList()
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initArticleList()
//    }

    private fun initArticleList(): Unit {
        var article1 = Article(
            "Title 1",
            null,
            null,
            null,
        )
        var article2 = Article(
            "Title 2",
            null,
            "Ben Cohen",
            "Body 2 Text"
        )
        var article3 = Article(
            "Title 3",
            null,
            "Omri Ovadia",
            "Body"
        )
        articleList = arrayOf(article1, article2, article3)
    }
}
