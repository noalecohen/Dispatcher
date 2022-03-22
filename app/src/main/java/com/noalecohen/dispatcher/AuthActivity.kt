package com.noalecohen.dispatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noalecohen.dispatcher.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        binding = ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.startup_frame_content, SplashFragment())
            .commit()
    }

}