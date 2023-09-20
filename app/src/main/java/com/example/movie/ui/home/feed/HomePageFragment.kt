package com.example.movie.ui.home.feed
import MainAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.movie.R
import com.example.movie.data.model.movieModel.TMDBResponse
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.home.feed.homePageRvAdapter.CarouselAdapter
import com.example.movie.utils.Constants
import com.example.movie.utils.UtilFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate)  {

    private lateinit var carouselAdapter: CarouselAdapter

    private val unexpectedErrorString: String by lazy { getString(R.string.unexpectedErrorString) }
    private val networkErrorOrServerAccessError: String by lazy { getString(R.string.networkErrorOrServerAccessError) }
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val timerDelay: Long = 5000
    private var currentPage = 0
    val navController by lazy { findNavController() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedScrollView = binding.nestedScrollView
        binding.sliderRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.sliderRecyclerView)


        getHomePageCarousel()
        getHomePageFeed()
        UtilFunctions.setupNestedScrollViewWithAnimatedBottomNavHiding(nestedScrollView,requireActivity().findViewById(R.id.bottomNav))

    }


    private fun getHomePageCarousel() {
        val call = RetrofitClient.tmdbService.getMoviesByCategory("upcoming")

        call.enqueue(object : Callback<TMDBResponse> {
            override fun onResponse(call: Call<TMDBResponse>, response: Response<TMDBResponse>) {
                if (response.isSuccessful) {
                    val movieList = response.body()?.results ?: emptyList()

                    carouselAdapter = CarouselAdapter(movieList)
                    binding.sliderRecyclerView.adapter = carouselAdapter

                    handler.postDelayed(updateCarousel, timerDelay)
                } else {
                    // Handle error here
                }
            }

            override fun onFailure(call: Call<TMDBResponse>, t: Throwable) {
                // Handle failure here
            }
        })
    }


    private fun getHomePageFeed() {

        val mainModels = mutableListOf<TMDBResponse>()

        Constants.categories.forEach { category ->
            val call = RetrofitClient.tmdbService.getMoviesByCategory(category)
            call.enqueue(object : Callback<TMDBResponse> {
                override fun onResponse(call: Call<TMDBResponse>, response: Response<TMDBResponse>) {

                    if (response.isSuccessful) {
                        val tmdbResponse = response.body()
                        tmdbResponse?.let {
                            mainModels.add(it)
                            binding.homepageRvMain.adapter = MainAdapter(mainModels, navController)
                        }
                    } else {
                        showSnackbar(unexpectedErrorString, R.color.snackBarDanger)
                    }
                }

                override fun onFailure(call: Call<TMDBResponse>, t: Throwable) {
                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarDanger)
                }
            })
        }
    }

    private val updateCarousel: Runnable = object : Runnable {
        override fun run() {
            val layoutManager = binding.sliderRecyclerView.layoutManager as LinearLayoutManager
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            if (lastVisibleItemPosition >= 0) {
                currentPage = (lastVisibleItemPosition + 1) % carouselAdapter.itemCount
            }

            binding.sliderRecyclerView.smoothScrollToPosition(currentPage)
            handler.postDelayed(this, timerDelay)
        }
    }}








