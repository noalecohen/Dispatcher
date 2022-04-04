package com.noalecohen.dispatcher.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.adapter.ArticleAdapter
import com.noalecohen.dispatcher.databinding.FragmentHomeBinding
import com.noalecohen.dispatcher.decoration.TopSpacingItemDecoration
import com.noalecohen.dispatcher.view.activity.AuthActivity
import com.noalecohen.dispatcher.view.activity.MainActivity
import com.noalecohen.dispatcher.viewmodel.ArticlesViewModel
import com.noalecohen.dispatcher.viewmodel.AuthViewModel
import com.noalecohen.dispatcher.viewstate.RequestState

class HomeFragment : Fragment() {
    private val articlesModel: ArticlesViewModel by activityViewModels()
    private val authModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private var adapter = ArticleAdapter { position -> onListItemClick(position) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        subscribeObservers()
        setSignoutButton()
    }

    private fun setRecyclerView() {
        articlesModel.articlesStateLiveData.postValue(RequestState.Loading)
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.homeRecyclerView.addItemDecoration(TopSpacingItemDecoration())
    }

    private fun onListItemClick(position: Int) {
        Toast.makeText(context, adapter.currentList[position].title, Toast.LENGTH_LONG).show()
    }

    private fun subscribeObservers() {
        articlesModel.articlesLiveData.observe(viewLifecycleOwner) { articles ->
            adapter.submitList(articles)
        }

        articlesModel.articlesStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Success -> {
                    articlesModel.articlesStateLiveData.postValue(RequestState.Idle)
                    (activity as MainActivity).showLoader(false)
                    if (adapter.currentList.isEmpty()) {
                        Toast.makeText(context, R.string.empty_response, Toast.LENGTH_LONG).show()
                    }
                }
                is RequestState.Error -> {
                    articlesModel.articlesStateLiveData.postValue(RequestState.Idle)
                    (activity as MainActivity).showLoader(false)
                    adapter.currentList.clear()
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                }
                is RequestState.Loading -> {
                    (activity as MainActivity).showLoader(true)
                }
            }
        }
    }

    private fun setSignoutButton() {
        binding.homeSignOutButton.setOnClickListener {
            authModel.signOut()
            var intent = Intent(activity, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}