package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.ForgetPasswordSendEmailResponseModel
import com.example.movie.databinding.FragmentSendResetPasswordMailBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.data.repository.AuthClient
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendResetPasswordMailFragment :  BaseFragment<FragmentSendResetPasswordMailBinding>(FragmentSendResetPasswordMailBinding::inflate){

    private val utilFunction = UtilFunctions()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        forgetPasswordSendMailClickListener()

    }


    private fun forgetPasswordSendMailClickListener() {

        binding.buttonForgetPasswordNext.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()


            if (!utilFunction.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = "Please enter a valid e-mail address."
                return@setOnClickListener
            }




            utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, true)

            val authApi = AuthClient.getAuthApi()
            val call = authApi.forgetPasswordSendEmail(email)

            call.enqueue(object : Callback<ForgetPasswordSendEmailResponseModel?> {
                override fun onResponse(call: Call<ForgetPasswordSendEmailResponseModel?>, response: Response<ForgetPasswordSendEmailResponseModel?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_resetPasswordFragment_to_otpFragment)
                            showSnackbar("${authResponse.success}")

                        } else {

                            utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)
                            showSnackbar("Something went wrong!")
                        }
                    } else {
                        utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage)
                        } catch (e: Exception) {
                            showSnackbar("Server error.")
                        }
                    }
                }

                override fun onFailure(call: Call<ForgetPasswordSendEmailResponseModel?>, t: Throwable) {
                    utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)

                    showSnackbar("Network error or server access error.")
                }
            })
        }

    }



}