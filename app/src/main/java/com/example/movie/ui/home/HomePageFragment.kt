package com.example.movie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.databinding.FragmentSecondScreenBinding
import com.example.movie.databinding.FragmentThirdScreenBinding
import com.example.movie.ui.base.BaseFragment

class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate)  {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "HomePage"

    }

}