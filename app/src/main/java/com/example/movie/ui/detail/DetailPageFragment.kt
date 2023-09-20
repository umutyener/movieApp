package com.example.movie.ui.detail

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.data.model.movieModel.Movie
import com.example.movie.data.model.movieModel.TMDBResponse
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentDetailPageBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.detail.detailPageRvAdapter.DetailPageMovieAdapter
import com.example.movie.utils.Constants
import com.example.movie.utils.UtilFunctions
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DetailPageFragment :
    BaseFragment<FragmentDetailPageBinding>(FragmentDetailPageBinding::inflate) {

    private lateinit var movieAdapter: DetailPageMovieAdapter
    private var originalStatusBarColor: Int = 0
    private val transparentColor: Int = Color.parseColor("#00000000")
    private val args: DetailPageFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }
    private lateinit var movieDetail : Movie




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarTitle.isSelected = true
        binding.toolbarTitle.text = args.movieTitle

        UtilFunctions.setupNestedScrollViewWithAnimatedToolbarHiding(binding.nestedScrollView,binding.toolbarDetailPage)
        getMoviebyId()
        hideStatusBar()

    }


    private fun getMoviePoster(){

        Picasso.get().load(Constants.posterBaseUrl + args.movieImageUrl)
            .placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
            .into(binding.imageViewDetailPagePosterImage)
        Picasso.get().load(Constants.posterBaseUrl + args.movieImageUrl)
            .into(binding.ImageViewDetailPageBackroundImage)
    }
    private fun getMovieOverview() {
        val maxLinesToShow = 6
        val originalText = movieDetail.overview
        val buttonSeeMore = binding.buttonSeeMore
        val textViewMovieOverview = binding.textViewMovieStory
        val buttonSeeMoreText = binding.textViewSeeMoreButtonText

        textViewMovieOverview.text = originalText
        textViewMovieOverview.post {
            val layout = textViewMovieOverview.layout
            val lineCount = layout.lineCount

            Log.e("textViewMovieStory", textViewMovieOverview.text.toString())

            if (textViewMovieOverview.text.isEmpty()) {

                binding.textViewStoryLineMenuTitle.visibility = View.GONE
                binding.textViewMovieStory.visibility = View.GONE

            } else {

                if (lineCount <= maxLinesToShow) {
                    buttonSeeMore.visibility = View.GONE
                } else {
                    buttonSeeMore.visibility = View.VISIBLE
                    buttonSeeMoreText.text = "Show more"
                    textViewMovieOverview.maxLines = maxLinesToShow
                    buttonSeeMore.setOnClickListener {
                        buttonSeeMoreText.text = "Show more"
                        if (textViewMovieOverview.maxLines == Int.MAX_VALUE) {
                            textViewMovieOverview.maxLines = maxLinesToShow
                        } else {
                            textViewMovieOverview.maxLines = Int.MAX_VALUE
                            buttonSeeMoreText.text = "Show less"

                        }
                    }
                }
            }
        }
    }
    private fun getDetailPageSimilarMovie() {

        val call = RetrofitClient.tmdbService.getMoviesByCategory("${args.movieId}/similar")

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
    private fun getMoviebyId(){
        val call = RetrofitClient.tmdbService.getMovieDetail(args.movieId)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {

                        movieDetail = response.body()!!


                    getMoviePoster()
                    getMovieDescription()
                    getMovieOverview()
                    getDetailPageSimilarMovie()


                } else {
                     Log.e("Hata","Hata 1")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("Hata","Hata 2")
            }
        })
    }
    private fun getMovieDescription() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val releaseDate = sdf.parse(movieDetail.releaseDate)

        val cal = Calendar.getInstance()
        cal.time = releaseDate
        val year = cal.get(Calendar.YEAR)

        binding.textViewDetailPageDate.text = year.toString()
        binding.textViewDetailPageDuration.text = movieDetail.runtime.toString()
    }

    private fun hideStatusBar(){

        originalStatusBarColor = activity?.window?.statusBarColor ?: Color.TRANSPARENT

        activity?.window?.statusBarColor = transparentColor
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbarDetailPage) { view, insets ->
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.topMargin = insets.systemGestureInsets.top
            view.layoutParams = params
            insets
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        activity?.window?.statusBarColor = originalStatusBarColor
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)        }
}