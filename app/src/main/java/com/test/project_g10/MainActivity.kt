package com.test.project_g10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.os.postDelayed
import com.test.project_g10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     val TAG = this@MainActivity.toString()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))

        val intent = Intent(this, LessonsListActivity::class.java)
        // run the list of activities
        startActivity(intent)

    }
}