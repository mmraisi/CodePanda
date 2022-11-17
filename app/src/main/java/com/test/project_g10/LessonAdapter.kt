package com.test.project_g10;

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.test.project_g10.databinding.ItemLessonListBinding


class LessonAdapter(context: Context, var dataSource: ArrayList<Lesson>):ArrayAdapter<Lesson>(context , 0, dataSource){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentLesson = getItem(position)

        lateinit var itemLessonBinding:ItemLessonListBinding
        itemLessonBinding = ItemLessonListBinding.inflate(LayoutInflater.from(context), parent, false)

        var itemView = itemLessonBinding.root

        if (currentLesson != null) {
            itemLessonBinding.tvLessonTitle.setText(currentLesson.title)
            itemLessonBinding.tvLessonDuration.setText(currentLesson.duration)
            itemLessonBinding.imgBulletPoint.setImageResource(currentLesson.id_image)

            if(currentLesson.isCompleted){
                itemLessonBinding.imgDone.visibility = View.VISIBLE
            }
        }

        return itemView
    }
}

