package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.LoginResponseModel
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val emailValidString: String by lazy { getString(R.string.emailValidString) }
    private val passwordValidString: String by lazy { getString(R.string.passwordValidString) }
    private val unexpectedErrorString: String by lazy { getString(R.string.unexpectedErrorString) }
    private val networkErrorOrServerAccessError: String by lazy { getString(R.string.networkErrorOrServerAccessError) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButtonClickListener()
        loginToRegisterButtonClickListener()
        forgetPasswordButtonClickListener()

    }


    private fun loginButtonClickListener() {

        binding.buttonLogin.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()


            if (!UtilFunctions.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = emailValidString
                return@setOnClickListener
            }

            if (!UtilFunctions.isPasswordValid(password)) {
                binding.editTextTextPassword.error = passwordValidString
                return@setOnClickListener
            }

            UtilFunctions.buttonProgress(binding.buttonLogin, binding.progressBar, true)

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.login(email, password)

            call.enqueue(object : Callback<LoginResponseModel?> {
                override fun onResponse(
                    call: Call<LoginResponseModel?>,
                    response: Response<LoginResponseModel?>
                ) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
                        } else {

                            UtilFunctions.buttonProgress(
                                binding.buttonLogin,
                                binding.progressBar,
                                false
                            )
                            showSnackbar(unexpectedErrorString, R.color.snackBarDanger)
                        }
                    } else {
                        UtilFunctions.buttonProgress(binding.buttonLogin, binding.progressBar, false)
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage, R.color.snackBarCaution)
                        } catch (e: Exception) {
                            showSnackbar("$unexpectedErrorString Error Code: ${e.message}", R.color.snackBarDanger)
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponseModel?>, t: Throwable) {
                    UtilFunctions.buttonProgress(binding.buttonLogin, binding.progressBar, false)
                    findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)

                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarDanger)

                }
            })
        }

    }

    private fun loginToRegisterButtonClickListener() {
        binding.textViewCreateAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun forgetPasswordButtonClickListener() {
        binding.textViewForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

}
