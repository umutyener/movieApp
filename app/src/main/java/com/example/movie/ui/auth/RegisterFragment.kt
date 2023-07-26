package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.RegisterResponse
import com.example.movie.databinding.FragmentRegisterBinding
import com.example.movie.ui.base.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginAndRegisterButtonClickListener()
    }

    private fun loginAndRegisterButtonClickListener() {
        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val username = binding.editTextTextUsername.text.toString()

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.register(username, password, email)


            call.enqueue(object : Callback<RegisterResponse?> {
                override fun onResponse(call: Call<RegisterResponse?>, response: Response<RegisterResponse?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
                            showSnackbar("Kayit başarili $authResponse")

                        } else {

                            showSnackbar("$authResponse AuthResponse boş.")

                        }
                    } else {

                        showSnackbar(" kayit başarısız oldu")

                    }
                }

                override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
                    showSnackbar("Ağ hatası veya sunucu erişimi hatası.")
                }
            })
        }
    }
}