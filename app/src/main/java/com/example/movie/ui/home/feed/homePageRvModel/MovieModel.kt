package com.example.movie.ui.home.feed.homePageRvModel

import com.google.gson.annotations.SerializedName

data class MovieModel(


    val imageUrl: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("vote_average") val voteAverage: String,

    )
