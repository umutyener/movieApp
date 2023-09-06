package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.RegisterResponseModel
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentRegisterBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val emailValidString: String by lazy { getString(R.string.emailValidString) }
    private val passwordValidString: String by lazy { getString(R.string.passwordValidString) }
    private val unexpectedErrorString: String by lazy { getString(R.string.unexpectedErrorString) }
    private val networkErrorOrServerAccessError: String by lazy { getString(R.string.networkErrorOrServerAccessError) }
    private val usernameValidString: String by lazy { getString(R.string.usernameValidString) }
    private val privacyPolicyValidString: String by lazy { getString(R.string.privacyPolicyValidString) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButtonClickListener()
    }


    private fun registerButtonClickListener() {
        binding.buttonRegister.setOnClickListener {

            val username = binding.editTextUsername.text.toString()
            val email = binding.editTextEmailAdress.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (!fieldValidation(username, email, password)) {
                return@setOnClickListener
            }

            UtilFunctions.buttonProgress(binding.buttonRegister, binding.progressBar, true)


            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.register(username, password, email)


            call.enqueue(object : Callback<RegisterResponseModel?> {
                override fun onResponse(call: Call<RegisterResponseModel?>, response: Response<RegisterResponseModel?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                        }
                    } else {

                        UtilFunctions.buttonProgress(binding.buttonRegister, binding.progressBar, false)
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage)
                        } catch (e: Exception) {
                            showSnackbar(unexpectedErrorString, R.color.snackBarDanger)
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponseModel?>, t: Throwable) {
                    UtilFunctions.buttonProgress(binding.buttonRegister, binding.progressBar, false)

                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarDanger)
                }


            })
        }
    }

    private fun fieldValidation(username: String, email: String, password: String): Boolean {
        if (!UtilFunctions.isUsernameValid(username)) {
            binding.editTextUsername.error = usernameValidString
            return false
        }

        if (!UtilFunctions.isEmailValid(email)) {
            binding.editTextEmailAdress.error =  emailValidString
            return false
        }

        if (!UtilFunctions.isPasswordValid(password)) {
            binding.editTextPassword.error = passwordValidString
            return false
        }

        if (!binding.checkBox.isChecked) {
            showSnackbar(privacyPolicyValidString, R.color.snackBarCaution)
            return false
        }

        return true
    }


}
