package com.test.project_g10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import com.test.project_g10.databinding.ActivityLessonsListBinding

class LessonsListActivity : AppCompatActivity() {
    lateinit var dataSource: DataSource
    lateinit var binding:ActivityLessonsListBinding
    lateinit var lessonsArrayList:ArrayList<Lesson>
    lateinit var lessonsAdapter: LessonAdapter

    val TAG = this@LessonsListActivity.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLessonsListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()

        dataSource = DataSource.getInstance()

        Log.d(TAG, "Lesson Screen: lessons list count ${dataSource.lessonsArrayList.count()}")

        lessonsArrayList = dataSource.lessonsArrayList

        lessonsAdapter = LessonAdapter(this, lessonsArrayList)

        binding.lvLessons.adapter = lessonsAdapter

        binding.lvLessons.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, id ->
            val selectLesson = lessonsArrayList[position]
            val intent = Intent(this, LessonDetailActivity::class.java)
            intent.putExtra("EXTRA_LESSON", selectLesson)
            startActivity(intent)
        }

    }
}