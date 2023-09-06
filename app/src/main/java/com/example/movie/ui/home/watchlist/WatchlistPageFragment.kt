package com.example.movie.ui.home.watchlist

import android.os.Bundle
import android.view.View
import com.example.movie.databinding.FragmentWatchlistPageBinding
import com.example.movie.ui.baseFragment.BaseFragment



class WatchlistPageFragment: BaseFragment<FragmentWatchlistPageBinding>(FragmentWatchlistPageBinding::inflate)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.watchlistPageToolbar.title = "Watchlist Screen"

    }
}