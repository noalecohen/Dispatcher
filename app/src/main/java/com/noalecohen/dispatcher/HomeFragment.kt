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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayBody()
    }

    private fun displayBody() {
        var bodies = articleList.mapNotNull { it.body?.split(" ")?.take(2)?.joinToString(" ") }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, bodies)
        binding.homeListView.adapter = adapter
    }
}