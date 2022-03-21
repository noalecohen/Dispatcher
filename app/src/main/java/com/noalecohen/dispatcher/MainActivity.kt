package com.noalecohen.dispatcher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.noalecohen.dispatcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
        setBottomNavigation(savedInstanceState)
    }

    private fun bindingView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManager = supportFragmentManager
    }

    private fun setBottomNavigation(savedInstanceState: Bundle?) {
        var tabIndex = 1
        if (savedInstanceState != null) {
            tabIndex = savedInstanceState.get("index") as Int
        }
        binding.homePageBottomNav.getTabAt(tabIndex)?.select()

        binding.homePageBottomNav.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> fragmentManager!!.beginTransaction()
                        .replace(binding.homePageFrameContent.id, AccountFragment()).commit()
                    1 -> fragmentManager!!.beginTransaction()
                        .replace(binding.homePageFrameContent.id, HomeFragment()).commit()
                    2 -> fragmentManager!!.beginTransaction()
                        .replace(binding.homePageFrameContent.id, FavoritesFragment()).commit()
                    else -> {
                        Log.d("TAG", "Tab not found")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val index = binding.homePageBottomNav.selectedTabPosition
        outState.putInt("index", index)
    }
}