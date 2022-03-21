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
import viewModel.AccountViewModel

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val model: AccountViewModel by activityViewModels()

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
        subscribeObservers()
        binding.accountSaveButton.setOnClickListener {
            val title = binding.accountEditText.text.toString()
            binding.accountEditText.text.clear()
            model.addTitle(title)
        }
    }

    private fun subscribeObservers() {
        model.titles.observe(viewLifecycleOwner) { titles ->
            val titlesNotNull = titles.filterNotNull()
            val adapter = ArrayAdapter(requireContext(), layout.list_item, titlesNotNull)
            binding.accountListView.adapter = adapter
        }
    }
}