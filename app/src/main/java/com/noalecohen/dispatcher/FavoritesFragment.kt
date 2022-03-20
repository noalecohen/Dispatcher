package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noalecohen.dispatcher.databinding.FragmentFavoritesBinding
import model.BaseFragment

class FavoritesFragment : BaseFragment() {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        displayAuthor()
        return binding.root
    }

    private fun displayAuthor() {
        var authors = ""
        for (article in articleList) {
            if (article.author != null) {
                authors += article.author
            }
            authors += "\n\n"
        }
        binding.favoritesText.text = authors
    }
}