package com.noalecohen.dispatcher.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.noalecohen.dispatcher.databinding.ActivityMainBinding
import com.noalecohen.dispatcher.view.fragment.AccountFragment
import com.noalecohen.dispatcher.view.fragment.FavoritesFragment
import com.noalecohen.dispatcher.view.fragment.HomeFragment
import com.noalecohen.dispatcher.view.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
        setBottomNavigation(savedInstanceState)
        setSearchButton()
        showSearchLayout(false)
    }

    private fun bindingView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManager = supportFragmentManager
    }

    private fun setBottomNavigation(savedInstanceState: Bundle?) {
        var tabIndex = DEFAULT_TAB_INDEX
        if (savedInstanceState != null) {
            tabIndex = savedInstanceState.get(TAB_INDEX) as Int
        }
        binding.homePageBottomNav.getTabAt(tabIndex)?.select()

        binding.homePageBottomNav.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> fragmentManager.beginTransaction()
                        .replace(binding.homePageFrameContent.id, AccountFragment()).commit()
                    1 -> fragmentManager.beginTransaction()
                        .replace(binding.homePageFrameContent.id, HomeFragment()).commit()
                    2 -> fragmentManager.beginTransaction()
                        .replace(binding.homePageFrameContent.id, FavoritesFragment()).commit()
                    else -> {
                        Log.d(NOT_FOUND, "Tab not found")
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
        outState.putInt(TAB_INDEX, index)
    }

    fun showLoader(toShow: Boolean) {
        binding.mainProgressBar.visibility = if (toShow) View.VISIBLE else View.GONE
    }

    private fun setSearchButton() {
        binding.homePageHeader.setOnClickListenerIcon2 {
            fragmentManager.beginTransaction()
                .add(binding.searchLayoutPlaceholder.id, SearchFragment())
                .addToBackStack("search").commit()
            showSearchLayout(true)
        }
    }

    private fun showSearchLayout(toShow: Boolean) {
        binding.searchLayoutPlaceholder.visibility = if (toShow) View.VISIBLE else View.GONE
    }

    companion object {
        const val DEFAULT_TAB_INDEX = 1
        const val NOT_FOUND = "Not Found"
        const val TAB_INDEX = "index"
    }
}