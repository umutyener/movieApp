package com.example.movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.data.model.authModel.LoginResponseModel
import com.example.movie.data.repository.AuthClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun performLogin(email: String, password: String) {
        val authApi = AuthClient.getAuthApi()
        val call = authApi.login(email, password)

        call.enqueue(object : Callback<LoginResponseModel?> {
            override fun onResponse(call: Call<LoginResponseModel?>, response: Response<LoginResponseModel?>) {
                if (response.isSuccessful) {
                    val authResponse = response.body()
                    if (authResponse != null) {
                        _loginResult.value = LoginResult.Success
                    } else {
                        _loginResult.value = LoginResult.Failure("Response Error")
                    }
                } else {
                    try {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = JSONObject(errorBody).getString("error")
                        _loginResult.value = LoginResult.Failure(errorMessage)
                    } catch (e: Exception) {
                        _loginResult.value = LoginResult.Failure("Server error.")
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponseModel?>, t: Throwable) {
                _loginResult.value = LoginResult.Failure("Network error or server access error.")
            }
        })
    }

    sealed class LoginResult {
        object Success : LoginResult()
        data class Failure(val errorMessage: String) : LoginResult()
    }
}
