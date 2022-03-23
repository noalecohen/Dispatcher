package com.noalecohen.dispatcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentFavoritesBinding
import com.noalecohen.dispatcher.isValidInput
import com.noalecohen.dispatcher.update
import com.noalecohen.dispatcher.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {
    private val model: FavoritesViewModel by activityViewModels()
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArrayAdapter(requireContext(), R.layout.list_item, mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritesListView.adapter = adapter
        subscribeObservers()
        setSaveButton()
    }

    private fun setSaveButton() {
        binding.favoritesSaveButton.setOnClickListener {
            val author = binding.favoritesEditText.text.toString()
            if (binding.favoritesEditText.isValidInput()) {
                model.addAuthor(author)
            }
            binding.favoritesEditText.text.clear()
        }
    }

    private fun subscribeObservers() {
        model.authors.observe(viewLifecycleOwner) { authors ->
            var authorsNotNull = authors.filterNotNull()
            adapter.update(authorsNotNull)
        }
    }
}