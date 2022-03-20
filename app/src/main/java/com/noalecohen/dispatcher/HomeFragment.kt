package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noalecohen.dispatcher.databinding.FragmentHomeBinding
import model.BaseFragment

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        displayBody()
        return binding.root
    }

    private fun displayBody() {
        var bodies = ""

        for (article in articleList) {
            val articleBody = article.body?.split(" ")
            if (articleBody != null) {
                if (articleBody.isNotEmpty()) {
                    bodies += articleBody[0]
                }
                if (articleBody.size >= 2) {
                    bodies += " ${articleBody[1]}"
                }
            }
            bodies += "\n\n"
        }
        binding.homeText.text = bodies
    }
}