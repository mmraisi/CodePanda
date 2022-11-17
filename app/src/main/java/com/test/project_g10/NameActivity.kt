package com.test.project_g10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.project_g10.databinding.ActivityNameBinding

class NameActivity : AppCompatActivity() {

    lateinit var binding:ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}