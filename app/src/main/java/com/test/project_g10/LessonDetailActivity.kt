package com.test.project_g10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.project_g10.databinding.ActivityLessonDetailBinding
import java.io.Serializable

class LessonDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityLessonDetailBinding
    lateinit var dataSource: DataSource
    lateinit var lessonsArrayList:ArrayList<Lesson>
    var TAG = this@LessonDetailActivity.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataSource = DataSource.getInstance()
        if (intent != null) {
            val lessonFromScreen1:Serializable? = intent.getSerializableExtra("EXTRA_LESSON")
            if (lessonFromScreen1 != null) {
                val lesson:Lesson? = lessonFromScreen1 as? Lesson
                lessonsArrayList = dataSource.lessonsArrayList
                if (lesson != null) {
                    // output to the screen
                    binding.tvLessonTitle.setText("${lesson.id}. ${lesson.title}")
                    binding.tvLessonDuration.setText(lesson.duration)
                    binding.tvLessonInfo.setText(lesson.lessonInfo)
                    if (lesson.notes != "") {
                        binding.edtNotes.setText(lesson.notes)
                    }
                }


                binding.btnSaveNotes.setOnClickListener {
                    val notesFromUser = binding.edtNotes.text
                    for (eachLesson in lessonsArrayList) {
                        if (lesson != null) {
                            if (eachLesson.id == lesson.id) {
                                eachLesson.notes = notesFromUser.toString()
                            }

                        }
                    }
                }
            }
        }


    }
}