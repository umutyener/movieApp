package com.example.movie.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import retrofit2.Call
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.LoginResponseModel
import com.example.movie.ui.onboarding.onboardingScreens.repository.RetrofitClient
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.UtilFunction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val utilFunction = UtilFunction()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButtonClickListener()
        loginToRegisterButtonClickListener()

    }


    private fun loginButtonClickListener() {

      binding.buttonLogin.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()


            if (!utilFunction.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = "Please enter a valid e-mail address."
                return@setOnClickListener
            }

            if (!utilFunction.isPasswordValid(password)) {
                binding.editTextTextPassword.error = "The password field cannot be empty."
                return@setOnClickListener
            }


          utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, true)

          val authApi = RetrofitClient.getAuthApi()
            val call = authApi.login(email, password)

            call.enqueue(object : Callback<LoginResponseModel?> {
                override fun onResponse(call: Call<LoginResponseModel?>, response: Response<LoginResponseModel?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)

                        } else {
                            utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, false)
                            showSnackbar("${response.body()?.error} Incorrect username or password.")

                        }
                    } else {

                        showSnackbar("${response.body()?.error}")
                        utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, false)

                    }
                }

                override fun onFailure(call: Call<LoginResponseModel?>, t: Throwable) {
                    utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, false)

                    showSnackbar("Network error or server access error.")
                }
            })
        }

    }

    private fun loginToRegisterButtonClickListener(){
        binding.textViewCreateAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}