package com.example.movie.ui.auth

import android.os.Bundle
import retrofit2.Call
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.LoginResponse
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.ui.base.BaseFragment
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButtonClickListener()
        loginToRegisterButtonClickListener()
    }


    private fun loginButtonClickListener() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.login(username, password)

            call.enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
                            showSnackbar("Giris basarilli")

                        } else {

                            showSnackbar("AuthResponse boş.")
                        }
                    } else {

                        showSnackbar("${response.body()}")
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    showSnackbar("Ağ hatası veya sunucu erişimi hatası.")
                }
            })
        }

    }
    private fun loginToRegisterButtonClickListener(){
        binding.textViewLoginToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}