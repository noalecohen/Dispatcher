package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
        var authors = articleList.map { it.author }.filterNotNull()
        val adapter = ArrayAdapter<String?>(requireContext(), R.layout.list_item, authors)
        binding.favoritesListView.adapter = adapter
    }
}