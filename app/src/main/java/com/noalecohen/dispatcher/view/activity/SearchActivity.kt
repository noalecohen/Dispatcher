package com.noalecohen.dispatcher.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.noalecohen.dispatcher.adapter.ArticleAdapter
import com.noalecohen.dispatcher.databinding.ActivitySearchBinding
import com.noalecohen.dispatcher.view.decoration.TopSpacingItemDecoration
import com.noalecohen.dispatcher.viewmodel.ArticlesViewModel
import com.noalecohen.dispatcher.viewstate.RequestState


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val articlesModel: ArticlesViewModel by viewModels()
    private var adapter = ArticleAdapter { position -> onListItemClick(position) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.searchInputLayuot.setStartIconOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
        Toast.makeText(this, adapter.currentList[position].title, Toast.LENGTH_LONG).show()
    }

    private fun setRecyclerView() {
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.searchRecyclerView.addItemDecoration(TopSpacingItemDecoration())
    }

    private fun subscribeObservers() {
        articlesModel.searchArticlesLiveData.observe(this) { articles ->
            adapter.submitList(articles)
        }

        articlesModel.searchArticlesStateLiveData.observe(this) {
            when (it) {
                is RequestState.Success -> {
                    articlesModel.searchArticlesStateLiveData.postValue(RequestState.Idle)
                    showLoader(false)
                    showEmptyResultIndicator(adapter.currentList.isEmpty())
                }
                is RequestState.Error -> {
                    articlesModel.searchArticlesStateLiveData.postValue(RequestState.Idle)
                    showLoader(false)
                    showEmptyResultIndicator(true)
                    adapter.currentList.clear()
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
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