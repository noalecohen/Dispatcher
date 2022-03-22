package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.databinding.FragmentHomeBinding
import util.isValidInput
import viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private val model: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArrayAdapter(requireContext(), R.layout.list_item, mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeListView.adapter = adapter
        subscribeObservers()
        setSaveButton()
    }

    private fun setSaveButton() {
        binding.homeSaveButton.setOnClickListener {
            val body = binding.homeEditText.text.toString()
            if (isValidInput(binding.homeEditText.text.toString())) {
                model.addBody(body)
            }
            binding.homeEditText.text.clear()
        }
    }

    private fun subscribeObservers() {
        model.bodies.observe(viewLifecycleOwner) { bodies ->
            var bodiesNotNull = bodies.mapNotNull { it?.split(" ")?.take(2)?.joinToString(" ") }
            adapter.update(bodiesNotNull)
        }
    }
}