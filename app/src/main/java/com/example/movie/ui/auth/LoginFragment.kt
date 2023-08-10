package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.LoginResponseModel
import com.example.movie.data.repository.AuthClient
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.utils.UtilFunctions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val utilFunction = UtilFunctions()

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


            if (!utilFunction.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = "Please enter a valid e-mail address."
                return@setOnClickListener
            }

            if (!utilFunction.isPasswordValid(password)) {
                binding.editTextTextPassword.error = "The password field cannot be empty."
                return@setOnClickListener
            }


            utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, true)

            val authApi = AuthClient.getAuthApi()
            val call = authApi.login(email, password)

            call.enqueue(object : Callback<LoginResponseModel?> {
                override fun onResponse(
                    call: Call<LoginResponseModel?>,
                    response: Response<LoginResponseModel?>
                ) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            showSnackbar("${authResponse.success}",R.color.snackBarSafety)

                            findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
                        } else {

                            utilFunction.buttonProgress(
                                binding.buttonLogin,
                                binding.progressBar,
                                false
                            )
                            showSnackbar("Response Error")
                        }
                    } else {
                        utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, false)
                        try {
                            val errorBody = response.errorBody()?.string()
                            val errorMessage = JSONObject(errorBody).getString("error")
                            showSnackbar(errorMessage, R.color.snackBarCaution)
                        } catch (e: Exception) {
                            showSnackbar("Server error.")
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponseModel?>, t: Throwable) {
                    utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, false)

                    showSnackbar("Network error or server access error.")
                    findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)

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


/*
*
*
* package com.example.movie.ui.auth
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.ui.baseFragment.BaseFragment
import com.example.movie.utils.UtilFunctions
import com.example.movie.viewModel.AuthViewModel


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val utilFunction = UtilFunctions()

    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textViewCreateAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.textViewForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }



        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        loginButtonClickListener()


        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is AuthViewModel.LoginResult.Success -> {
                    // Başarılı girişi işle
                    findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
                }

                is AuthViewModel.LoginResult.Failure -> {
                    // Giriş başarısızlığını işle
                    utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, false)
                    showSnackbar(result.errorMessage, R.color.snackBarCaution)
                }
            }
        }
    }

    private fun loginButtonClickListener() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            if (!utilFunction.isEmailValid(email)) {
                binding.editTextTextEmailAddress.error = "Lütfen geçerli bir e-posta adresi girin."
                return@setOnClickListener
            }

            if (!utilFunction.isPasswordValid(password)) {
                binding.editTextTextPassword.error = "Şifre alanı boş bırakılamaz."
                return@setOnClickListener
            }

            utilFunction.buttonProgress(binding.buttonLogin, binding.progressBar, true)
            viewModel.performLogin(email, password)
        }
    }



}
*
*
* */