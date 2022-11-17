package com.test.project_g10

import java.io.Serializable

class Lesson(var id:Int, var title:String, var duration:String, var lessonInfo:String, var isCompleted:Boolean, var videoURL:String, var notes:String): Serializable {

    var id_image:Int = when(id){
        1-> R.drawable.one
        2-> R.drawable.two
        3-> R.drawable.three
        4-> R.drawable.four
        5-> R.drawable.five
        else -> R.drawable.bullet_point_icon
    }

    override fun toString(): String {
        return "Lesson(id=$id, title='$title', duration='$duration', isCompleted=$isCompleted)"
    }
}