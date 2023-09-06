package com.example.movie.ui.onboarding.onboardingScreens

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentOnboardingThirdScreenBinding
import com.example.movie.ui.baseFragment.BaseFragment

class OnBoardingThirdScreenFragment :   BaseFragment<FragmentOnboardingThirdScreenBinding>(FragmentOnboardingThirdScreenBinding::inflate)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonOnBoardingFinishClickListener()

    }

    private fun buttonOnBoardingFinishClickListener() {

        binding.buttonOnBoardingFinish.setOnClickListener {


            findNavController().navigate(R.id.action_viewPagerFragment_to_registerOrLoginFragment)



        }

    }
}


