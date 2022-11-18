package com.test.project_g10

import android.content.Intent
import android.content.SharedPreferences
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
    lateinit var sharedPrefs: SharedPreferences
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))
        sharedPrefs = this.getSharedPreferences("com_test_g10_PREFS_LESSONS", MODE_PRIVATE)

    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart from main screen:  pressed")

        if(checkIfNameExists()){
            fillDataSource() // fill the data source
            val intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkIfNameExists():Boolean {
        return if (sharedPrefs.contains("USERNAME")) {
            val username = sharedPrefs.getString("USERNAME", "")
            username?.isNotBlank() ?: false

        } else{
            false
        }

    }

    private fun getCompletedClasses(): MutableSet<String>? {
        return if(sharedPrefs.contains("COMPLETED_CLASSES")){
            sharedPrefs.getStringSet("test", null)
        } else{
            null
        }
    }

    private fun fillDataSource() {
        dataSource = DataSource.getInstance()
        with(sharedPrefs.edit()) {
            // write to sharedPreferences
            val completedClasses:MutableSet<String>? = getCompletedClasses()

            if(completedClasses != null){
                for(id in completedClasses){
                    dataSource.lessonsArrayList[id.toInt()].isCompleted = true
                }
            }
        }
    }
}