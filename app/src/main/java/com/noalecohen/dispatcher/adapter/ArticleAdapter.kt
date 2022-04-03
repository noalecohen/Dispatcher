package com.noalecohen.dispatcher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.AdapterArticleItemBinding
import com.noalecohen.dispatcher.model.response.Article

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //TODO: by id...
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: AdapterArticleItemBinding = AdapterArticleItemBinding.bind(itemView)

        fun bind(article: Article) {
            binding.adapterArticleItemDate.text = article.publishedAt
            binding.adapterArticleItemTitle.text = article.title
            binding.adapterArticleItemAuthor.text = article.author
            binding.adapterArticleItemAbstract.text = article.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}