package com.noalecohen.dispatcher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.noalecohen.dispatcher.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingView();
        setBottomNavigation();
    }

    private void bindingView(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();
    }

    private void setBottomNavigation() {
        binding.homePageBottomNav.getTabAt(1).select();
        binding.homePageBottomNav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragmentManager.beginTransaction().replace(binding.homePageFrameContent.getId(), new AccountFragment()).commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction().replace(binding.homePageFrameContent.getId(), new HomeFragment()).commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction().replace(binding.homePageFrameContent.getId(), new FavoritesFragment()).commit();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}