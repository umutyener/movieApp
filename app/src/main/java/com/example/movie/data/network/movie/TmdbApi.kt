package com.example.movie.data.network.movie

import com.example.movie.data.model.movieModel.Movie
import com.example.movie.data.model.movieModel.TMDBResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/{category}")
    fun getMoviesByCategory(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = com.example.movie.utils.Constants.tmdbApiKey
    ): Call<TMDBResponse>

    @GET("movie/{movieId}")
    fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = com.example.movie.utils.Constants.tmdbApiKey
    ): Call<Movie>
}
