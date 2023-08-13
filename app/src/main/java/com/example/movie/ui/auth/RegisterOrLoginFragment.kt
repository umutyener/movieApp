package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentRegisterOrLoginBinding
import com.example.movie.ui.baseFragment.BaseFragment


class RegisterOrLoginFragment :
    BaseFragment<FragmentRegisterOrLoginBinding>(FragmentRegisterOrLoginBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButtonClickListener()
        registerButtonClickListener()
        loginWithGoogleClickListener()


    }

    private fun loginButtonClickListener() {
        binding.textViewLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerOrLoginFragment_to_loginFragment2)

        }
    }

    private fun registerButtonClickListener() {
        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_registerOrLoginFragment_to_registerFragment2)

        }
    }

    private fun loginWithGoogleClickListener() {
        binding.buttonLoginWithGoogle.setOnClickListener {
            showSnackbar("Google login option coming soon!", R.color.snackBarSafety)
        }
    }


}