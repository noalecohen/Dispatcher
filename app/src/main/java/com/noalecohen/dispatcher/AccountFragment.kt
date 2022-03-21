package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.noalecohen.dispatcher.R.layout
import com.noalecohen.dispatcher.databinding.FragmentAccountBinding
import model.BaseFragment

class AccountFragment : BaseFragment() {
    private lateinit var binding: FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitles()
    }

    private fun displayTitles() {
        var titles = articleList.mapNotNull { it.title }
        val adapter = ArrayAdapter<String?>(requireContext(), layout.list_item, titles)
        binding.accountListView.adapter = adapter
    }
}