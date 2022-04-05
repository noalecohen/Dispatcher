package com.noalecohen.dispatcher.view.viewholder

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.noalecohen.dispatcher.adapter.ArticleAdapter
import com.noalecohen.dispatcher.databinding.AdapterArticleItemBinding
import com.noalecohen.dispatcher.model.response.Article
import com.squareup.picasso.Picasso
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ArticleViewHolder(itemView: View, private val onItemClick: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var binding: AdapterArticleItemBinding = AdapterArticleItemBinding.bind(itemView)

    init {
        itemView.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(article: Article) {
        binding.adapterArticleItemDate.text = OffsetDateTime.parse(article.publishedAt)
            .format(DateTimeFormatter.ofPattern(ArticleAdapter.DATE_PATTERN, Locale.ENGLISH))
        binding.adapterArticleItemTitle.text = article.title
        binding.adapterArticleItemAuthor.text = article.author
        binding.adapterArticleItemAbstract.text = article.description
        Picasso.get().load(article.urlToImage).into(binding.adapterArticleItemImage)
    }

    override fun onClick(view: View) {
        onItemClick(adapterPosition)
    }
}