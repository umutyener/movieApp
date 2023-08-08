package com.example.movie.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.databinding.FragmentViewPagerHomeBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.onboarding.OnBoardingScreenViewPagerAdapter


class HomeScreenViewPagerFragment :
    BaseFragment<FragmentViewPagerHomeBinding>(FragmentViewPagerHomeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList = arrayListOf<Fragment>(
            HomePageFragment(),
            WatchlistPageFragment(),
            SearchPageFragment(),
            ProfilePageFragment(),


            )
        val adapter = HomeScreenViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        }
        )

    }




}
