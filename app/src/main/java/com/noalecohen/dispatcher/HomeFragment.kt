package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.noalecohen.dispatcher.databinding.FragmentHomeBinding
import model.BaseFragment

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        displayBody()
        return binding.root
    }

    private fun displayBody() {
        var bodies = articleList.filter { it.body != null }
            .map { it.body?.split(" ")?.take(2)?.joinToString(" ") }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, bodies)
        binding.homeListView.adapter = adapter
    }
}