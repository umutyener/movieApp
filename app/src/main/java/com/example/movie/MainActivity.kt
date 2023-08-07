package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      //  dataProcessingWithDeepLink()
    }


    /*

    private fun dataProcessingWithDeepLink(){
        val data: Uri? = intent?.data
        if (data != null && data.scheme == "https" && data.host == "example.com" && data.path == "/reset_password" && data.encodedQuery != null) {
            val encodedQuery = data.encodedQuery!!.substringAfter("reset_token=")




        }
    }

    */


}

