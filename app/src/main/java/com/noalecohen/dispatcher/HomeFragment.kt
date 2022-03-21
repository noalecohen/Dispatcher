package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.databinding.FragmentHomeBinding
import viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val model: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        binding.homeSaveButton.setOnClickListener {
            val body = binding.homeEditText.text.toString()
            binding.homeEditText.text.clear()
            model.addBody(body)
        }
    }

    private fun subscribeObservers() {
        model.bodies.observe(viewLifecycleOwner) { bodies ->
            var bodiesNotNull = bodies.mapNotNull { it?.split(" ")?.take(2)?.joinToString(" ") }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, bodiesNotNull)
            binding.homeListView.adapter = adapter
        }
    }
}