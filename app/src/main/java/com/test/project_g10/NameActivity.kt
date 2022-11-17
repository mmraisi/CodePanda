package com.test.project_g10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.project_g10.databinding.ActivityNameBinding

class NameActivity : AppCompatActivity() {

    lateinit var binding:ActivityNameBinding
    lateinit var dataSource: DataSource
    var TAG = this@NameActivity.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSource = DataSource.getInstance()

        Log.d(TAG, "Username: ${dataSource.username}")

        binding.btnSaveName.setOnClickListener {
            val nameFromNameScreen = binding.edtName.text.toString()

            val isValidName = validateUsername(nameFromNameScreen)

            if(isValidName){
                dataSource.username = nameFromNameScreen
                Log.d(TAG, "Username: ${dataSource.username}")
                val intent = Intent(this, LessonsListActivity::class.java)
                startActivity(intent)
            }
            else{
                binding.edtName.error = "Please insert a valid name"
            }


        }

    }

    override fun onStart() {
        super.onStart()
        dataSource = DataSource.getInstance()
        Log.d(TAG, "onStart:  pressed")
        if(dataSource.username.isNotBlank()){
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateUsername(name:String):Boolean {
        if(name.isBlank()){
            return false

        }
        for (letter in name){
            if(!letter.isLetter()){
                return false
            }
        }
        return true
    }
}