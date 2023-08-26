package com.example.movie.ui.home.feed
import MainAdapter
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.data.model.movieModel.TMDBResponse
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.home.feed.homePageRvAdapter.CarouselAdapter
import com.example.movie.utils.Constants
import com.example.movie.utils.UtilFunctions
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate)  {

    private lateinit var carouselAdapter: CarouselAdapter

    private val unexpectedErrorString: String by lazy { getString(R.string.unexpectedErrorString) }
    private val networkErrorOrServerAccessError: String by lazy { getString(R.string.networkErrorOrServerAccessError) }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nestedScrollView = binding.nestedScrollView
        binding.sliderRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        getHomePageCarousel()
        getHomePageFeed()
        UtilFunctions.setupNestedScrollViewWithAnimatedBottomNavHiding(nestedScrollView,requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav))

    }


    private fun getHomePageCarousel(){


        val call = RetrofitClient.tmdbService.getMoviesByCategory("upcoming")

            call.enqueue(object : Callback<TMDBResponse> {
                override fun onResponse(call: Call<TMDBResponse>, response: Response<TMDBResponse>) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: emptyList()

                        carouselAdapter = CarouselAdapter(movieList)
                        binding.sliderRecyclerView.adapter = carouselAdapter

                    } else {
                    }
                }

                override fun onFailure(call: Call<TMDBResponse>, t: Throwable) {

                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarCaution)

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
                            binding.homepageRvMain.adapter = MainAdapter(mainModels)
                        }
                    } else {
                        showSnackbar("$unexpectedErrorString", R.color.snackBarDanger)
                    }
                }

                override fun onFailure(call: Call<TMDBResponse>, t: Throwable) {
                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarDanger)
                }
            })
        }
    }


}


