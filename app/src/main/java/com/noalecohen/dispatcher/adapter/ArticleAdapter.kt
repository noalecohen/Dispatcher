package com.noalecohen.dispatcher.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.AdapterArticleItemBinding
import com.noalecohen.dispatcher.model.response.Article
import com.squareup.picasso.Picasso
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ArticleAdapter(private val onItemClick: (position: Int) -> Unit) :
    ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //TODO: by id...
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    class ArticleViewHolder(itemView: View, private val onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var binding: AdapterArticleItemBinding = AdapterArticleItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(article: Article) {
            binding.adapterArticleItemDate.text = OffsetDateTime.parse(article.publishedAt)
                .format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH))
            binding.adapterArticleItemTitle.text = article.title
            binding.adapterArticleItemAuthor.text = article.author
            binding.adapterArticleItemAbstract.text = article.description
            Picasso.get().load(article.urlToImage).into(binding.adapterArticleItemImage)
        }

        override fun onClick(view: View) {
            onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_article_item, parent, false)
        return ArticleViewHolder(view, onItemClick)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        const val DATE_PATTERN = "EEEE MMM dd, yyyy"
    }
}