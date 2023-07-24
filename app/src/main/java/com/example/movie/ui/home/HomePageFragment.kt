package com.example.movie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.databinding.FragmentSecondScreenBinding
import com.example.movie.databinding.FragmentThirdScreenBinding

class HomePageFragment : Fragment() {

    private lateinit var binding : FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        binding.toolbar.setTitle("HomePage")
        return binding.root
    }

}