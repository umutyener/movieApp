package com.example.movie.data.model.authModel

data class LoginResponseModel(
    val access_token: String,
    val refresh_token: String,
    val error : String
)