package com.example.movie.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.databinding.FragmentOnboardingViewPagerBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.onboarding.onboardingScreens.OnBoardingFirstScreenFragment
import com.example.movie.ui.onboarding.onboardingScreens.OnBoardingSecondScreenFragment
import com.example.movie.ui.onboarding.onboardingScreens.OnBoardingThirdScreenFragment

class OnBoardingScreenViewPagerFragment :  BaseFragment<FragmentOnboardingViewPagerBinding>(FragmentOnboardingViewPagerBinding::inflate) {

    private val pageIndicatorViews = ArrayList<View>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFirstScreenFragment(),
            OnBoardingSecondScreenFragment(),
            OnBoardingThirdScreenFragment()
        )
        val adapter = OnBoardingScreenViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        pageIndicatorViews.add(binding.pageIndicator1)
        pageIndicatorViews.add(binding.pageIndicator2)
        pageIndicatorViews.add(binding.pageIndicator3)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updatePageIndicator(position)
            }
        }
        )

    }

    private fun updatePageIndicator(currentPosition: Int) {
        for (i in 0 until pageIndicatorViews.size) {
            pageIndicatorViews[i].isSelected = i == currentPosition
        }

}
}


