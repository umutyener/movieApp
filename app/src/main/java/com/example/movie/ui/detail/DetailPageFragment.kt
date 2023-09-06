package com.example.movie.ui.detail


import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.data.model.movieModel.TMDBResponse
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentDetailPageBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.detail.detailPageRvAdapter.DetailPageMovieAdapter
import com.example.movie.utils.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPageFragment :
    BaseFragment<FragmentDetailPageBinding>(FragmentDetailPageBinding::inflate) {


    private lateinit var movieAdapter: DetailPageMovieAdapter
    private var originalStatusBarColor: Int = 0
    private val transparentColor: Int = Color.parseColor("#00000000")
    private val args: DetailPageFragmentArgs by navArgs()
    val navController by lazy { findNavController() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarTitle.isSelected = true
        binding.toolbarTitle.text = args.movieTitle
        originalStatusBarColor = activity?.window?.statusBarColor ?: Color.TRANSPARENT


        Picasso.get().load(Constants.posterBaseUrl + args.movieImageUrl).into(binding.imageViewDetailPagePosterImage)
        Picasso.get().load(Constants.posterBaseUrl + args.movieImageUrl)
            .into(binding.ImageViewDetailPageBackroundImage)


        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbarDetailPage) { view, insets ->
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = insets.systemGestureInsets.top
            view.layoutParams = params
            insets
        }

        activity?.window?.statusBarColor = transparentColor
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getHomePageCarousel()

    }

    private fun getHomePageCarousel() {
        val call = RetrofitClient.tmdbService.getMoviesByCategory("upcoming")

        call.enqueue(object : Callback<TMDBResponse> {
            override fun onResponse(call: Call<TMDBResponse>, response: Response<TMDBResponse>) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: emptyList()

                    val orientation = resources.configuration.orientation
                    val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 3
                    val gridLayoutManager = GridLayoutManager(requireContext(), spanCount)

                    binding.rvMovieChild.layoutManager = gridLayoutManager

                    if (movieList.isNotEmpty()) {
                        movieAdapter = DetailPageMovieAdapter(movieList, navController)
                        binding.rvMovieChild.adapter = movieAdapter
                    }

                } else {
                    // Handle error here
                }
            }

            override fun onFailure(call: Call<TMDBResponse>, t: Throwable) {
                // Handle failure here
            }
        })
    }


        override fun onDestroyView() {
            super.onDestroyView()

            activity?.window?.statusBarColor = originalStatusBarColor
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
