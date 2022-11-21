package com.test.project_g10

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.test.project_g10.databinding.ActivityLessonDetailBinding
import java.io.Serializable

class LessonDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityLessonDetailBinding
    lateinit var dataSource: DataSource
    lateinit var sharedPrefs: SharedPreferences
    lateinit var lessonsArrayList: ArrayList<Lesson>
    var TAG = this@LessonDetailActivity.toString()
    lateinit var selectedLesson: Lesson
    var t:Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        dataSource = DataSource.getInstance()
        sharedPrefs = this.getSharedPreferences(resources.getString(R.string.SHARED_PREFS_FILE_KEY), MODE_PRIVATE)

        val selectedLessonPosition = dataSource.selectedLessonPosition

        if (selectedLessonPosition != null) {
            selectedLesson = dataSource.lessonsArrayList[selectedLessonPosition]

            lessonsArrayList = dataSource.lessonsArrayList
            // output to the screen
            binding.tvLessonTitle.text = "${selectedLesson.id}. ${selectedLesson.title}"
            binding.tvLessonDuration.text = selectedLesson.duration
            binding.tvLessonInfo.text = selectedLesson.lessonInfo
            if (selectedLesson.notes != "") {
                binding.edtNotes.setText(selectedLesson.notes)
            }

            binding.btnWatchLesson.setOnClickListener {
                val webview = binding.wvVideoPlayer
                webview.settings.javaScriptEnabled = true
                webview.loadUrl(selectedLesson.videoURL)
            }

            binding.btnSaveNotes.setOnClickListener {
                val notesFromUser = binding.edtNotes.text.toString()

                if(notesFromUser.isNotBlank()){
                    selectedLesson.notes = notesFromUser
                    addNotesToPrefs()
                    t?.cancel()
                    t = Toast.makeText(
                        this@LessonDetailActivity,
                        "Notes Saved",
                        Toast.LENGTH_SHORT
                    )
                    t?.show()
                }
                else{
                    t?.cancel()
                    t = Toast.makeText(
                        this@LessonDetailActivity,
                        "Note is empty",
                        Toast.LENGTH_SHORT
                    )
                    t?.show()
                }
            }

            binding.btnMarkComplete.setOnClickListener {
                selectedLesson.isCompleted = true

                addCompletedClass()
                finish()
            }
        }

    }

    private fun addCompletedClass(){

        var completedClasses:MutableSet<String>? = sharedPrefs.getStringSet(resources.getString(R.string.PREFS_COMPLETED_CLASSES), null)

        Log.d(TAG, "CompletedClass: $completedClasses")

        if (completedClasses != null) {
            completedClasses.add("${selectedLesson.id}")
        }
        else{
            completedClasses = mutableSetOf("${selectedLesson.id}")
        }
        Log.d(TAG, "CompletedClass: $completedClasses")
        with(sharedPrefs.edit()) {
            // write to sharedPreferences
            remove(resources.getString(R.string.PREFS_COMPLETED_CLASSES))
            apply()
            putStringSet(resources.getString(R.string.PREFS_COMPLETED_CLASSES), completedClasses) // key value pair
            apply()
        }
    }
    private fun addNotesToPrefs(){

        val prefStringName = "${resources.getString(R.string.PREFS_NOTE)}${selectedLesson.id}" //Note_id

        with(sharedPrefs.edit()) {
            // write to sharedPreferences
            putString(prefStringName, binding.edtNotes.text.toString()) // key value pair
            apply()
        }
    }

    override fun onPause() {
        super.onPause()
        t?.cancel() // cancel any toasts if exists
    }
}