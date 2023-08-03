    package com.example.movie.data.network.auth

    import com.example.movie.data.model.authModel.ForgetPasswordResponseModel
    import com.example.movie.data.model.authModel.LoginResponseModel
    import com.example.movie.data.model.authModel.OtpResponseModel
    import com.example.movie.data.model.authModel.RegisterResponseModel
    import retrofit2.Call
    import retrofit2.http.Field
    import retrofit2.http.FormUrlEncoded
    import retrofit2.http.POST


    interface AuthApi {

        // Login

        @FormUrlEncoded
        @POST("login/")
        fun login(
            @Field("email") email: String,
            @Field("password") password: String
        ): Call<LoginResponseModel>

        // Register

        @FormUrlEncoded
        @POST("register/")
        fun register(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("email") email: String
        ): Call<RegisterResponseModel>

        // Forget password Send Email

        @FormUrlEncoded
        @POST("sendEmail/")
        fun forgetPasswordSendEmail(
            @Field("email") email: String
        ): Call<ForgetPasswordResponseModel>

    }