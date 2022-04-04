package com.noalecohen.dispatcher.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.view.viewholder.ArticleViewHolder

class ArticleAdapter(private val onItemClick: (position: Int) -> Unit) :
    ListAdapter<Article, ArticleViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //TODO: by id...
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
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