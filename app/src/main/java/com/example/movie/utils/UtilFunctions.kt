package com.example.movie.utils

import android.view.View
import android.widget.Button
import android.widget.ProgressBar

class UtilFunctions {






    //  To verify login and registration fields

    public fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    public fun isUsernameValid(username: String): Boolean {
        return username.length in 3..15
    }

    public fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }



    //  To verify login and registration fields





    //  Button and ProgressBar show/hide operations

    public fun buttonProgress(button: Button, progressBar: ProgressBar, isEnabled : Boolean) {

        if(isEnabled){

            button.isEnabled = false
            progressBar.visibility = View.VISIBLE

        }else{

            button.isEnabled = true
            progressBar.visibility =  View.GONE
        }
    }

    //  Button and ProgressBar show/hide operations






}