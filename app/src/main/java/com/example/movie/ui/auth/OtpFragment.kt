package com.example.movie.ui.auth

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.ForgetPasswordResponseModel
import com.example.movie.databinding.FragmentOtpBinding
import com.example.movie.databinding.FragmentResetPasswordBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.ui.onboarding.onboardingScreens.repository.RetrofitClient
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpFragment :  BaseFragment<FragmentOtpBinding>(FragmentOtpBinding::inflate){


    private val utilFunction = UtilFunctions()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

          //  verifyOtpClickListener()
        testVerifyOtpClickListener()

        //setupOtpFields()

       // otpEditText1.requestFocus()
      //  showKeyboard()

    }





    private fun testVerifyOtpClickListener(){


        binding.buttonForgetPasswordNext.setOnClickListener {



        }

    }







    /*private fun verifyOtpClickListener() {

        binding.buttonForgetPasswordNext.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()


            if (!utilFunction.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = "Please enter a valid e-mail address."
                return@setOnClickListener
            }



            utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, true)

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.forgetPassword(email)

            call.enqueue(object : Callback<ForgetPasswordResponseModel?> {
                override fun onResponse(call: Call<ForgetPasswordResponseModel?>, response: Response<ForgetPasswordResponseModel?>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
                        } else {

                            utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)
                            showSnackbar("Response Error")
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

                override fun onFailure(call: Call<ForgetPasswordResponseModel?>, t: Throwable) {
                    utilFunction.buttonProgress(binding.buttonForgetPasswordNext, binding.progressBar, false)

                    showSnackbar("Network error or server access error.")
                }
            })
        }

    }*/


}
