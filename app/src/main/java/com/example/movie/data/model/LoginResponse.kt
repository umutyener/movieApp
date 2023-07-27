package com.example.movie.data.model

data class LoginResponse(
    val access_token: String,
    val refresh_token: String,
    val message : String
)