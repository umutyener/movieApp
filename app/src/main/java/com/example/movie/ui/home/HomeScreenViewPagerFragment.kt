package com.example.movie.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.R
import com.example.movie.databinding.FragmentViewPagerHomeBinding
import com.example.movie.ui.baseFragment.BaseFragment


class HomeScreenViewPagerFragment :
    BaseFragment<FragmentViewPagerHomeBinding>(FragmentViewPagerHomeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            HomePageFragment(),
            SearchPageFragment(),
            WatchlistPageFragment(),
            ProfilePageFragment()
        )

        val adapter = HomeScreenViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        binding.viewPager.isUserInputEnabled = false

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> binding.viewPager.currentItem = 0
                R.id.searchFragment -> binding.viewPager.currentItem = 1
                R.id.watchlistFragment -> binding.viewPager.currentItem = 2
                R.id.profileFragment -> binding.viewPager.currentItem = 3
            }
            true
        }
    }
}