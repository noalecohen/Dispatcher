package com.noalecohen.dispatcher.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.noalecohen.dispatcher.databinding.FragmentSearchBinding
import com.noalecohen.dispatcher.viewmodel.ArticlesViewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val articlesModel: ArticlesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        Log.d("SEARCH FRAGMENT", "success")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArrowButton()
        setCancelButton()
    }

    private fun setCancelButton() {
        binding.searchInputLayuot.setEndIconOnClickListener {
            Toast.makeText(context, "Cancel", Toast.LENGTH_LONG).show()
        }
    }

    private fun setArrowButton() {
        binding.searchInputLayuot.setStartIconOnClickListener {
            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
            articlesModel.fetchTopHeadLinesByKeyword(binding.searchInputEditText.text.toString())
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.home_page_frame_content, HomeFragment())?.commit()
        }
    }
}