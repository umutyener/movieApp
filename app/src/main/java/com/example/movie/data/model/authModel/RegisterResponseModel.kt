package com.example.movie.data.model.authModel

data class RegisterResponseModel(
    val message: String? = null,
    val username: String? = null,
    val email: String? = null,
    val error: String? = null
)