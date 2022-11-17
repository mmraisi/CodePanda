package com.test.project_g10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.project_g10.databinding.ActivityLessonDetailBinding
import java.io.Serializable

class LessonDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityLessonDetailBinding
    var TAG = this@LessonDetailActivity.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null) {
            val lessonFromScreen1:Serializable? = intent.getSerializableExtra("EXTRA_LESSON")
            if (lessonFromScreen1 != null) {
                val lesson:Lesson? = lessonFromScreen1 as? Lesson

                if (lesson != null) {
                    // output to the screen
                    binding.tvLessonTitle.setText("${lesson.id}. ${lesson.title}")
                    binding.tvLessonDuration.setText(lesson.duration)
                    binding.tvLessonInfo.setText(lesson.lessonInfo)
                }
            }
        }
    }
}