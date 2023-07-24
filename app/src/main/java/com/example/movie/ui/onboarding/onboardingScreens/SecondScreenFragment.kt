package com.example.movie.ui.onboarding.onboardingScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.R
import com.example.movie.databinding.FragmentSecondScreenBinding
import com.example.movie.ui.base.BaseFragment

class SecondScreenFragment :   BaseFragment<FragmentSecondScreenBinding>(FragmentSecondScreenBinding::inflate)  {
    private val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextButtonAndSkipButtonClickListener()

    }

    private fun nextButtonAndSkipButtonClickListener() {


        binding.nextButtonSecond.setOnClickListener {
            viewPager?.setCurrentItem(1)
        }

        binding.skipButton.setOnClickListener {


            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)


        }

    }
}


