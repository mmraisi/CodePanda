package com.test.project_g10

class DataSource {
    // create a private constructor
    private constructor() {
        lessonsArrayList = ArrayList()
        lessonsArrayList.add(Lesson(1,"Introduction to the Course", "12 min", false, "", ""))
        lessonsArrayList.add(Lesson(2,"What is Javascript?", "30 min", false, "", ""))
        lessonsArrayList.add(Lesson(3,"Variables and conditionals", "1 hr 20 min", true,"", ""))
        lessonsArrayList.add(Lesson(4,"Loops", "38 min", false,"", ""))
        lessonsArrayList.add(Lesson(5,"Functions", "40 min", false,"", ""))
    }

    companion object {
        @Volatile
        private lateinit var instance: DataSource

        fun getInstance(): DataSource {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = DataSource()
                }
                return instance
            }
        }
    }

    var lessonsArrayList:ArrayList<Lesson> = arrayListOf()
    var username = ""

    // put the data you want to store in the singleton here

    // put any functions / operations you want to perform on data in the singleton here

}