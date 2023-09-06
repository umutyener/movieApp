package com.example.movie.ui.home.search

import android.os.Bundle
import android.view.View
import com.example.movie.databinding.FragmentSearchPageBinding
import com.example.movie.ui.baseFragment.BaseFragment

class SearchPageFragment: BaseFragment<FragmentSearchPageBinding>(FragmentSearchPageBinding::inflate)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchPageToolbar.title = "Search Screen"

    }
}