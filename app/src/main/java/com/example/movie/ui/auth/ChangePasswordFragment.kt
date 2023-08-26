package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movie.R
import com.example.movie.data.model.authModel.ChangePasswordResponseModel
import com.example.movie.data.repository.RetrofitClient
import com.example.movie.databinding.FragmentChangePasswordBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {

    private val passwordValidString: String by lazy { getString(R.string.passwordValidString) }
    private val unexpectedErrorString: String by lazy { getString(R.string.unexpectedErrorString) }
    private val networkErrorOrServerAccessError: String by lazy { getString(R.string.networkErrorOrServerAccessError) }
    private val passwordMatchValidString: String by lazy { getString(R.string.passwordMatchValidString) }


    private val args: ChangePasswordFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        changePasswordButtonClickListener()
    }


    private fun changePasswordButtonClickListener() {

        binding.buttonChangePassword.setOnClickListener {

            val password = binding.editTextCreateNewPassword.text.toString()
            val passwordAgain = binding.editTextCreateNewPasswordAgain.text.toString()
            val resetToken = args.resetToken

            if (!UtilFunctions.isPasswordValid(password)) {
                binding.editTextCreateNewPassword.error = passwordValidString
                return@setOnClickListener
            }

            if (!UtilFunctions.isPasswordValid(passwordAgain)) {
                binding.editTextCreateNewPasswordAgain.error = passwordValidString
                return@setOnClickListener
            }

            if (password != passwordAgain) {
                binding.editTextCreateNewPasswordAgain.error = passwordMatchValidString
                return@setOnClickListener
            }


            UtilFunctions.buttonProgress(binding.buttonChangePassword, binding.progressBar, true)

            val authApi = RetrofitClient.getAuthApi()
            val call = authApi.changePassword(resetToken, password)

            call.enqueue(object : Callback<ChangePasswordResponseModel?> {
                override fun onResponse(
                    call: Call<ChangePasswordResponseModel?>,
                    response: Response<ChangePasswordResponseModel?>
                ) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            findNavController().navigate(R.id.action_changePasswordFragment_to_loginFragment)
                            showSnackbar("${authResponse.success}", R.color.snackBarSafety)

                        } else {

                            UtilFunctions.buttonProgress(
                                binding.buttonChangePassword,
                                binding.progressBar,
                                false
                            )
                            showSnackbar(unexpectedErrorString)
                        }
                    } else {
                        UtilFunctions.buttonProgress(
                            binding.buttonChangePassword,
                            binding.progressBar,
                            false
                        )
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage, R.color.snackBarDanger)
                        } catch (e: Exception) {
                            showSnackbar(
                                "$unexpectedErrorString Error Code: ${e.message}",
                                R.color.snackBarDanger
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ChangePasswordResponseModel?>, t: Throwable) {
                    UtilFunctions.buttonProgress(
                        binding.buttonChangePassword,
                        binding.progressBar,
                        false
                    )

                    showSnackbar(networkErrorOrServerAccessError, R.color.snackBarCaution)
                }
            })
        }


    }
}