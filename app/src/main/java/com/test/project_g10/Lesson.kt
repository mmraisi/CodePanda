package com.test.project_g10

import java.io.Serializable

class Lesson(var id:Int, var title:String, var duration:String, var isCompleted:Boolean, var videoURL:String, var notes:String): Serializable {

    var id_image:Int
    init {
        id_image = R.drawable.bullet_point_icon
    }

    override fun toString(): String {
        return "Lesson(id=$id, title='$title', duration='$duration', isCompleted=$isCompleted)"
    }
}