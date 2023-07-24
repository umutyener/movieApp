package com.example.movie.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentLoginBinding
import com.example.movie.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentRegisterBinding.inflate(inflater, container, false)


        binding.textViewRegisterToLogin.setOnClickListener {

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }

        binding.buttonRegister.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_homePageFragment)

        }




        return binding.root

    }


}