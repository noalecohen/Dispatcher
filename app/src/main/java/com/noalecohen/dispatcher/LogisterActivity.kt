package com.noalecohen.dispatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LogisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logister)
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.startup_frame_content, SplashFragment())
            .commit()
    }
}