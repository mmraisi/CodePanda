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
import kotlin.text.Typography.less

class MainActivity : AppCompatActivity() {
    val TAG = this@MainActivity.toString()

    lateinit var binding: ActivityMainBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))

    }

    override fun onStart() {
        super.onStart()

        dataSource = DataSource.getInstance()
        sharedPrefs = this.getSharedPreferences("com_test_g10_PREFS_LESSONS", MODE_PRIVATE)

        Log.d(TAG, "onStart from main screen:  pressed")

        if (checkIfNameExists()) {
            fillDataSource() // fill the data source
            val intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkIfNameExists(): Boolean {
        return if (sharedPrefs.contains("USERNAME")) {
            val username = sharedPrefs.getString("USERNAME", "")
            username?.isNotBlank() ?: false

        } else {
            false
        }

    }

    private fun getCompletedClasses(): MutableSet<String>? {
        return if (sharedPrefs.contains("COMPLETED_CLASSES")) {
            sharedPrefs.getStringSet("COMPLETED_CLASSES", null)
        } else {
            null
        }
    }

    private fun fillDataSource() {
        with(sharedPrefs.edit()) {
            // write to sharedPreferences
            val completedClasses: MutableSet<String>? = getCompletedClasses()

            if (completedClasses != null) {

                for (lesson in dataSource.lessonsArrayList) {
                    if (lesson.id.toString() in completedClasses) {
                        lesson.isCompleted = true
                    }

                }
            }

            for (lesson in dataSource.lessonsArrayList){
                val note:String? = sharedPrefs.getString("Note_${lesson.id}", null)
                if(note != null){
                    lesson.notes = note
                }
            }
        }
    }
}