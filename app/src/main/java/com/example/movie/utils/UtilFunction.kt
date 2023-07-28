package com.example.movie.utils

class UtilFunction {












    //  To verify login and registration fields

    public fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    public fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    //  To verify login and registration fields
}