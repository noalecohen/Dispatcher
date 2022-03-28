package com.noalecohen.dispatcher.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.FragmentHomeBinding
import com.noalecohen.dispatcher.update
import com.noalecohen.dispatcher.view.activity.AuthActivity
import com.noalecohen.dispatcher.viewmodel.ArticlesViewModel
import com.noalecohen.dispatcher.viewmodel.AuthViewModel

class HomeFragment : Fragment() {
    private val articlesModel: ArticlesViewModel by activityViewModels()
    private val authModel: AuthViewModel by activityViewModels()
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
        setSignoutButton()
        setSaveButton()
    }

    private fun subscribeObservers() {
        articlesModel.articlesLiveData.observe(viewLifecycleOwner) { articles ->
            adapter.update(articles.mapNotNull { it.title })
        }
    }

    private fun setSignoutButton() {
        binding.homeSignOutButton.setOnClickListener {
            authModel.signOut()
            var intent = Intent(activity, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setSaveButton() {
        binding.homeSaveButton.setOnClickListener {
            val country = binding.homeEditText.text.toString()
            articlesModel.fetchTopHeadlinesByCountry(country)
        }
    }
}