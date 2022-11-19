package com.test.project_g10

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
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

    override fun onStart() {
        super.onStart()
        sharedPrefs = this.getSharedPreferences("com_test_g10_PREFS_LESSONS", MODE_PRIVATE)
        dataSource = DataSource.getInstance()
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
}