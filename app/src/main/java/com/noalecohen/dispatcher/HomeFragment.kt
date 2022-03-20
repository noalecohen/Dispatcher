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

        for(article in articleList) {
            val a = article.Body?.split(" ")
            if(a != null){
                if(a.isNotEmpty()){
                    bodies += a[0]
                }
                if(a.size >= 2){
                    bodies += " ${a[1]}"
                }
            }
            bodies += "\n\n"
        }
        binding.homeText.text = bodies
    }
}