package com.example.movie.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentSplashBinding
import com.example.movie.ui.base.BaseFragment


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Handler().postDelayed({

            if (onBoardingFinished()){
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)

            }else{
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 1000)

    }





    private fun onBoardingFinished() : Boolean{


        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)


        return sharedPref.getBoolean("Finished", false)
    }
}
