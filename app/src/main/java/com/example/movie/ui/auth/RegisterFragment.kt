package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.RegisterResponseModel
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentRegisterBinding
import com.example.movie.ui.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButtonClickListener()
        registerToLoginButtonClickListener()
    }

    private fun registerButtonClickListener() {
        binding.buttonRegister.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val username = binding.editTextTextUsername.text.toString()

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.register(username, password, email)


            call.enqueue(object : Callback<RegisterResponseModel?> {
                override fun onResponse(call: Call<RegisterResponseModel?>, response: Response<RegisterResponseModel?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            showSnackbar("${authResponse.message}")

                        } else {

                            if (authResponse != null) {
                                showSnackbar("AuthResponse boş.${authResponse.message}")
                            }

                        }
                    } else {

                        showSnackbar("${response.body()?.message}")

                    }
                }

                override fun onFailure(call: Call<RegisterResponseModel?>, t: Throwable) {
                    showSnackbar("Ağ hatası veya sunucu erişimi hatası.")
                }
            })
        }
    }
    private fun registerToLoginButtonClickListener(){
        binding.textViewRegisterToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}