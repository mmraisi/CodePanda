package com.test.project_g10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.test.project_g10.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding:ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

//        Handler().postDelayed(Runnable {
//
//            val i = Intent(this@SplashScreenActivity, MainActivity::class.java)
//            startActivity(i)
//            // close this activity
//            finish()
//        }, 3000)
    }
}