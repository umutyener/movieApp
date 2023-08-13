package com.example.movie.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movie.R
import com.example.movie.databinding.FragmentProfilePageBinding
import com.example.movie.databinding.FragmentWatchlistPageBinding
import com.example.movie.ui.baseFragment.BaseFragment



class ProfilePageFragment: BaseFragment<FragmentProfilePageBinding>(FragmentProfilePageBinding::inflate)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profilePageToolbar.title = "Profile Screen"

    }
}