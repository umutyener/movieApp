package com.example.movie.data.repository

import com.example.movie.data.network.auth.AuthApi
import com.example.movie.data.network.movie.TmdbApi
import com.example.movie.utils.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitClient {


    val tmdbService: TmdbApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseUrlTMDB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TmdbApi::class.java)

    }

    private val authService = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAuthApi(): AuthApi {
        return authService.create(AuthApi::class.java)
    }
}