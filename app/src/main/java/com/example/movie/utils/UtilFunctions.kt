package com.example.movie.utils

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.abs

class UtilFunctions {


    companion object {



        fun setupNestedScrollViewWithAnimatedBottomNavHiding(
            nestedScrollView: NestedScrollView,
            bottomNavigationView: BottomNavigationView
        ) {
            var isBottomNavVisible = true
            val scrollThreshold = 40

            nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                val dy = scrollY - oldScrollY

                if (dy > 0 && isBottomNavVisible) {
                    // Scroll down: hide the bottom navigation
                    bottomNavigationView.animate().translationY(bottomNavigationView.height.toFloat())
                        .setInterpolator(AccelerateInterpolator(2f))
                        .start()
                    isBottomNavVisible = false
                } else if (dy < 0) {
                    // Scroll up: show the bottom navigation
                    if (abs(dy) > scrollThreshold) {
                        bottomNavigationView.animate().translationY(0f)
                            .setInterpolator(DecelerateInterpolator(2f))
                            .start()
                        isBottomNavVisible = true
                    }
                }
            }
        }






        //  To verify login and registration fields

         fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

         fun isUsernameValid(username: String): Boolean {
            return username.length in 3..15
        }

         fun isPasswordValid(password: String): Boolean {
            return password.length >= 6
        }



        //  Button and ProgressBar show/hide operations

         fun buttonProgress(button: Button, progressBar: ProgressBar, isEnabled: Boolean) {

            if (isEnabled) {

                button.isEnabled = false
                progressBar.visibility = View.VISIBLE

            } else {

                button.isEnabled = true
                progressBar.visibility = View.GONE
            }
        }


    }


}

