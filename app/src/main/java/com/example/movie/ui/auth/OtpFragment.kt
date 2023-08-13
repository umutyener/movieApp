package com.example.movie.ui.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.data.model.authModel.RegisterResponseModel
import com.example.movie.data.repository.AuthClient
import com.example.movie.databinding.FragmentOtpBinding
import com.example.movie.ui.baseFragment.BaseFragment
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpFragment : BaseFragment<FragmentOtpBinding>(FragmentOtpBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tryAnotherEmailAdressButtonClickListener()

    }






        private fun tryAnotherEmailAdressButtonClickListener() {

            binding.textViewTryAnotherEmailAdress.setOnClickListener {
                findNavController().navigate(R.id.action_otpFragment_to_resetPasswordFragment)

            }
        }
    }