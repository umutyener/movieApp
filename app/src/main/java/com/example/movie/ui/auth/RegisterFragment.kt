package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.RegisterResponseModel
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentRegisterBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val utilFunction = UtilFunctions()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButtonClickListener()
       // registerToLoginButtonClickListener()
    }


    private fun registerButtonClickListener() {
        binding.buttonRegister.setOnClickListener {



            val username = binding.editTextUsername.text.toString()
            val email = binding.editTextEmailAdress.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (!fieldValidation(username, email, password)) {
                return@setOnClickListener
            }



            utilFunction.buttonProgress(binding.buttonRegister, binding.progressBar, true)


            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.register(username, password, email)


            call.enqueue(object : Callback<RegisterResponseModel?> {
                override fun onResponse(
                    call: Call<RegisterResponseModel?>,
                    response: Response<RegisterResponseModel?>
                ) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_registerFragment_to_homePageFragment)

                        }
                    } else {

                        utilFunction.buttonProgress(binding.buttonRegister, binding.progressBar, false)
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage)
                        } catch (e: Exception) {
                            showSnackbar("Server error.")
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponseModel?>, t: Throwable) {
                    utilFunction.buttonProgress(binding.buttonRegister, binding.progressBar, false)

                    showSnackbar("Network error or server access error.")
                }


            })
        }
    }

    private fun fieldValidation(username: String, email: String, password: String): Boolean {
        if (!utilFunction.isUsernameValid(username)) {
            binding.editTextUsername.error = "The username field must contain at least 3 characters and no more than 15 characters!"
            return false
        }

        if (!utilFunction.isEmailValid(email)) {
            binding.editTextEmailAdress.error = "Please enter a valid e-mail address!"
            return false
        }

        if (!utilFunction.isPasswordValid(password)) {
            binding.editTextPassword.error = "The password field must be at least 6 characters!"
            return false
        }

        if (!binding.checkBox.isChecked) {
            showSnackbar("Please accept the Privacy Policy.")
            return false
        }

        return true
    }


}
