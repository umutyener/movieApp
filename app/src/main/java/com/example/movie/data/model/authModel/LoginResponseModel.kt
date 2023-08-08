package com.example.movie.data.model.authModel

data class LoginResponseModel(
    val access_token: String? = null,
    val refresh_token: String? = null,
    val error: String? = null,
    val success: String? = null

)
