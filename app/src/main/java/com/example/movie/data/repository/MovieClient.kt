package com.example.movie.data.repository

import com.example.movie.data.network.auth.AuthApi
import com.example.movie.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovieApi(): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}
