package com.example.movie.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.core.view.marginTop
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
                    bottomNavigationView.animate().translationY(bottomNavigationView.height.toFloat())
                        .setInterpolator(AccelerateInterpolator(2f))
                        .start()
                    isBottomNavVisible = false
                } else if (dy < 0) {
                    if (abs(dy) > scrollThreshold) {
                        bottomNavigationView.animate().translationY(0f)
                            .setInterpolator(DecelerateInterpolator(2f))
                            .start()
                        isBottomNavVisible = true
                    }
                }
            }
        }

        fun setupNestedScrollViewWithAnimatedToolbarHiding(
            nestedScrollView: NestedScrollView,
            toolbar: androidx.appcompat.widget.Toolbar
        ) {
            val scrollThreshold = 40
            var scrollY = 0

            nestedScrollView.setOnScrollChangeListener { _, _, _, _, _ ->
                val newScrollY = nestedScrollView.scrollY

                val dy = newScrollY - scrollY

                if (dy > 0 && toolbar.translationY == 0f) {
                    // Scroll down: hide the toolbar
                    toolbar.animate().translationY(-toolbar.height.toFloat())
                        .setInterpolator(AccelerateInterpolator(2f))
                        .start()
                } else if (dy < 0 || newScrollY == 0) {
                    // Scroll up or at the top: show the toolbar
                    if (abs(dy) > scrollThreshold || newScrollY == 0) {
                        toolbar.animate().translationY(0f)
                            .setInterpolator(DecelerateInterpolator(2f))
                            .start()
                    }
                }

                scrollY = newScrollY
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

