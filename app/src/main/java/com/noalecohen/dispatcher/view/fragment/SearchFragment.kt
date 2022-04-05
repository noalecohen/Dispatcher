package com.noalecohen.dispatcher.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.noalecohen.dispatcher.adapter.ArticleAdapter
import com.noalecohen.dispatcher.databinding.FragmentSearchBinding
import com.noalecohen.dispatcher.view.decoration.TopSpacingItemDecoration
import com.noalecohen.dispatcher.viewmodel.ArticlesViewModel
import com.noalecohen.dispatcher.viewstate.RequestState


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val articlesModel: ArticlesViewModel by activityViewModels()
    private var adapter = ArticleAdapter { position -> onListItemClick(position) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        setCancelButton()
        subscribeObservers()
        setRecyclerView()
        setDoneAction()
    }

    private fun setCancelButton() {
        binding.searchInputLayuot.setEndIconOnClickListener {
            binding.searchInputEditText.text?.clear()
        }
    }

    private fun setBackButton() {
        articlesModel.searchArticlesLiveData.postValue(emptyList())
        adapter.currentList.clear()
        binding.searchInputLayuot.setStartIconOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setDoneAction() =
        binding.searchInputEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                articlesModel.fetchFilterResults(binding.searchInputEditText.text.toString())
                articlesModel.searchArticlesStateLiveData.postValue(RequestState.Loading)
            }
            false
        }

    private fun onListItemClick(position: Int) {
        Toast.makeText(context, adapter.currentList[position].title, Toast.LENGTH_LONG).show()
    }

    private fun setRecyclerView() {
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerView.addItemDecoration(TopSpacingItemDecoration())
    }

    private fun subscribeObservers() {
        articlesModel.searchArticlesLiveData.observe(viewLifecycleOwner) { articles ->
            adapter.submitList(articles)
        }

        articlesModel.searchArticlesStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Success -> {
                    articlesModel.searchArticlesStateLiveData.postValue(RequestState.Idle)
//                    articlesModel.searchArticlesLiveData.value?.isEmpty()
//                        ?.let { it1 -> showEmptyResultIndicator(it1) }
                }
                is RequestState.Error -> {
                    articlesModel.searchArticlesStateLiveData.postValue(RequestState.Idle)
                    showEmptyResultIndicator(true)
                    //adapter.currentList.clear()
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                }
                is RequestState.Loading -> {
                    showLoader(true)
                    showEmptyResultIndicator(false)
                }
                is RequestState.Idle -> {
                    showLoader(false)
                }
            }
        }
    }

    private fun showLoader(toShow: Boolean) {
        binding.searchProgressBar.visibility = if (toShow) View.VISIBLE else View.GONE
    }

    private fun showEmptyResultIndicator(toShow: Boolean) {
        binding.searchEmptyResultImage.visibility = if (toShow) View.VISIBLE else View.GONE
        binding.searchEmptyResultMessage.visibility = if (toShow) View.VISIBLE else View.GONE
    }
}