package com.noalecohen.dispatcher.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.noalecohen.dispatcher.R
import com.noalecohen.dispatcher.databinding.ActivityAuthBinding
import com.noalecohen.dispatcher.view.fragment.SplashFragment


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.startup_frame_content, SplashFragment())
            .commit()
    }

    fun showLoader(toShow: Boolean) {
        if (toShow) {
            binding.authProgressBar.visibility = View.VISIBLE
        } else {
            binding.authProgressBar.visibility = View.GONE
        }
    }
}