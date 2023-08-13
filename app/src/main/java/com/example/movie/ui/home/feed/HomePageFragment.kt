package com.example.movie.ui.home.feed
import android.os.Bundle
import android.view.View
import com.example.movie.data.model.movieModel.TMDBResponse
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.home.feed.homePageRvAdapter.MainAdapter
import com.example.movie.ui.home.feed.homePageRvModel.MainModel
import com.example.movie.ui.home.feed.homePageRvModel.MovieModel
import com.example.movie.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate)  {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "HomePage"

        val mainModels = mutableListOf<MainModel>()

        Constants.categories.forEach { category ->
            val call = RetrofitClient.tmdbService.getMoviesByCategory(category, Constants.tmdbApiKey)
            call.enqueue(object : Callback<TMDBResponse> {
                override fun onResponse(call: Call<TMDBResponse>, response: Response<TMDBResponse>) {

                    if (response.isSuccessful) {
                        val movies = response.body()?.results
                        val mainModel = MainModel(category.capitalize(), movies?.map { movieResult ->
                            MovieModel(Constants.POSTER_BASE_URL + movieResult.posterPath, movieResult.originalTitle, movieResult.voteAverage.toString())
                        } ?: emptyList())

                        mainModels.add(mainModel)
                        binding.homepageRvMain.adapter = MainAdapter(mainModels)
                    } else {
                        showSnackbar("Error")
                    }
                }

                override fun onFailure(call: Call<TMDBResponse>, t: Throwable) {
                    showSnackbar("Error")
                }
            })
        }
    }
}


