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
    lateinit var binding:ActivityWelcomeScreenBinding
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
        sharedPrefs = this.getSharedPreferences("com_test_g10_PREFS_LESSONS", MODE_PRIVATE)
        dataSource = DataSource.getInstance()

        // Get username from shared prefs
        val username = sharedPrefs.getString("USERNAME", "")
        binding.tvWelcomeName.setText("Welcome back, $username")

        //Get completed classes from shared prefs
        val completedClasses:MutableSet<String>? = sharedPrefs.getStringSet("COMPLETED_CLASSES", null)

        // Total no of classes in data source
        val totalClass = dataSource.lessonsArrayList.size

        //Calculate percentage of completed classes
        val completedPercentage = (100 / totalClass) * (completedClasses?.size ?: 0)
        binding.tvCompletePercentage.setText("You have completed $completedPercentage% of the course")

        //Set completed classes
        if (completedClasses != null) {
            binding.tvCompleted.setText(completedClasses.size.toString())
        }
        else {
            binding.tvCompleted.setText("0")
        }


        //Calculate remaining classes
        var remainingClasses = totalClass - (completedClasses?.size ?: 0)
        binding.tvRemaining.setText(remainingClasses.toString())

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
        }

        binding.lvDeleteAll.setOnClickListener {
            with(sharedPrefs.edit()) {
                clear() // to delete all the keys
                apply()
            }
            dataSource.reset()
            finish()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity();
    }
}