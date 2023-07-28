package com.example.movie.data.network

import com.example.movie.data.model.authModel.LoginResponseModel
import com.example.movie.data.model.authModel.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthApi {
    @FormUrlEncoded
    @POST("login/")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>

    @FormUrlEncoded
    @POST("register/")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): Call<RegisterResponseModel>
}