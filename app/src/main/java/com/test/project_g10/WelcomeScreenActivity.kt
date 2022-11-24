package com.test.project_g10

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.ActionMode
import com.test.project_g10.databinding.ActivityWelcomeScreenBinding

class WelcomeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityWelcomeScreenBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        sharedPrefs = this.getSharedPreferences(resources.getString(R.string.SHARED_PREFS_FILE_KEY), MODE_PRIVATE)
        dataSource = DataSource.getInstance()

        // Get username from dataSource
        val username = dataSource.username
        binding.tvWelcomeName.text = "${resources.getString(R.string.welcome_back)}, $username"

        //Get completed classes from dataSource
        var numberOfCompletedClasses: Int = 0

        for (lesson in dataSource.lessonsArrayList) {
            if (lesson.isCompleted) {
                numberOfCompletedClasses++
            }
        }

        // Total no of classes in data source
        val totalClass = dataSource.lessonsArrayList.size

        //Calculate percentage of completed classes
        val completedPercentage = (100 / totalClass) * (numberOfCompletedClasses)
        binding.tvCompletePercentage.text = "${resources.getString(R.string.you_have_completed)} $completedPercentage% ${resources.getString(R.string.of_the_course)}"

        //Set completed classes
        binding.tvCompleted.text = numberOfCompletedClasses.toString()


        //Calculate remaining classes
        var remainingClasses = totalClass - numberOfCompletedClasses
        binding.tvRemaining.text = remainingClasses.toString()

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
        }

        binding.tvDeleteAll.setOnClickListener {
            with(sharedPrefs.edit()) {
                clear() // to delete all the keys
                apply()
            }
            dataSource.reset()
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity();
    }
}