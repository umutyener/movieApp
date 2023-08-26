package com.example.movie.ui.detail

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.example.movie.databinding.FragmentDetailPageBinding

class DetailPageFragment :
    Fragment(R.layout.fragment_detail_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity?.window?.statusBarColor = Color.TRANSPARENT
        }
    }
}
