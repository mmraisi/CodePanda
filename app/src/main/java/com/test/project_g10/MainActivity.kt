package com.test.project_g10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.project_g10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     val TAG = this@MainActivity.toString()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))
    }
}