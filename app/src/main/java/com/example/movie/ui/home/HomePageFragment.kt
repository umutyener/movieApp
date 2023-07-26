package com.example.movie.ui.home
import android.os.Bundle
import android.view.View
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.ui.base.BaseFragment

class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate)  {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "HomePage"

    }

}