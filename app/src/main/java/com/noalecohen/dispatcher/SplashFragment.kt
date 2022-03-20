package com.noalecohen.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Intent
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment

class SplashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toMainActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    fun toMainActivity() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}