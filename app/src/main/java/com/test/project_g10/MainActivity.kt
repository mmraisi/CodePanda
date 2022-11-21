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
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec

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
        sharedPrefs = this.getSharedPreferences(resources.getString(R.string.SHARED_PREFS_FILE_KEY), MODE_PRIVATE)

        Log.d(TAG, "onStart from main screen:  pressed")
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if (checkIfNameExists()) {
                fillDataSource() // fill the data source
                val intent = Intent(this, WelcomeScreenActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, NameActivity::class.java)
                startActivity(intent)
            }

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)


    }

    private fun checkIfNameExists(): Boolean {
        return if (sharedPrefs.contains(resources.getString(R.string.PREFS_USERNAME))) {
            val username = sharedPrefs.getString(resources.getString(R.string.PREFS_USERNAME), "")
            username?.isNotBlank() ?: false

        } else {
            false
        }

    }

    private fun getCompletedClasses(): MutableSet<String>? {
        return if (sharedPrefs.contains(resources.getString(R.string.PREFS_COMPLETED_CLASSES))) {
            sharedPrefs.getStringSet(resources.getString(R.string.PREFS_COMPLETED_CLASSES), null)
        } else {
            null
        }
    }

    private fun fillDataSource() {
        with(sharedPrefs.edit()) {

            val username = sharedPrefs.getString(resources.getString(R.string.PREFS_USERNAME), "")
            dataSource.username = if(username?.isNotBlank() == true) username else ""

            val completedClasses: MutableSet<String>? = getCompletedClasses()

            if (completedClasses != null) {

                for (lesson in dataSource.lessonsArrayList) {
                    if (lesson.id.toString() in completedClasses) {
                        lesson.isCompleted = true
                    }

                }
            }

            for (lesson in dataSource.lessonsArrayList){
                val note:String? = sharedPrefs.getString("${resources.getString(R.string.PREFS_NOTE)}${lesson.id}", null) // Note_id
                if(note != null){
                    lesson.notes = note
                }
            }
        }
    }
}