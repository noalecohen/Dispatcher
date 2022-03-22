package com.noalecohen.dispatcher

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.R.layout
import com.noalecohen.dispatcher.databinding.FragmentAccountBinding
import util.isValidInput
import viewModel.AccountViewModel

class AccountFragment : Fragment() {
    private val model: AccountViewModel by activityViewModels()
    private lateinit var binding: FragmentAccountBinding
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArrayAdapter(requireContext(), layout.list_item, mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(
            "TAG",
            "New instance of AccountFragment created, with orientation: " + resources.configuration.orientation.toString()
        )
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountListView.adapter = adapter
        subscribeObservers()
        setSaveButton()
    }

    private fun setSaveButton() {
        binding.accountSaveButton.setOnClickListener {
            val title = binding.accountEditText.text.toString()
            if (isValidInput(binding.accountEditText.text.toString())) {
                model.addTitle(title)
            }
            binding.accountEditText.text.clear()
        }
    }

    private fun subscribeObservers() {
        model.titles.observe(viewLifecycleOwner) { titles ->
            val titlesNotNull = titles.filterNotNull()
            adapter.update(titlesNotNull)
        }
    }
}
