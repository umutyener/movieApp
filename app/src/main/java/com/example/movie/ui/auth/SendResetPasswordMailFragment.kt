package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.ForgetPasswordSendEmailResponseModel
import com.example.movie.databinding.FragmentSendResetPasswordMailBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendResetPasswordMailFragment :  BaseFragment<FragmentSendResetPasswordMailBinding>(FragmentSendResetPasswordMailBinding::inflate){

    private val emailValidString: String by lazy { getString(R.string.emailValidString) }
    private val unexpectedErrorString: String by lazy { getString(R.string.unexpectedErrorString) }
    private val networkErrorOrServerAccessError: String by lazy { getString(R.string.networkErrorOrServerAccessError) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        forgetPasswordSendMailClickListener()

    }


    private fun forgetPasswordSendMailClickListener() {

        binding.buttonForgetPasswordNext.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()
            if (!UtilFunctions.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = emailValidString
                return@setOnClickListener
            }


            UtilFunctions.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, true)

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.forgetPasswordSendEmail(email)

            call.enqueue(object : Callback<ForgetPasswordSendEmailResponseModel?> {
                override fun onResponse(call: Call<ForgetPasswordSendEmailResponseModel?>, response: Response<ForgetPasswordSendEmailResponseModel?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_resetPasswordFragment_to_otpFragment)
                            showSnackbar("${authResponse.success}")

                        } else {

                            UtilFunctions.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)
                            showSnackbar(unexpectedErrorString, R.color.snackBarDanger)
                        }
                    } else {
                        UtilFunctions.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage)
                        } catch (e: Exception) {
                            showSnackbar(unexpectedErrorString, R.color.snackBarDanger)
                        }
                    }
                }

                override fun onFailure(call: Call<ForgetPasswordSendEmailResponseModel?>, t: Throwable) {
                    UtilFunctions.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)

                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarDanger)
                }
            })
        }

    }
}