package com.test.project_g10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.os.postDelayed
import com.test.project_g10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     val TAG = this@MainActivity.toString()

    lateinit var binding: ActivityMainBinding
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))

    }

    override fun onStart() {
        super.onStart()
        dataSource = DataSource.getInstance()
        Log.d(TAG, "onStart from main screen:  pressed")
        if(dataSource.username.isNotBlank()){
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }
}