package com.test.project_g10

class DataSource {
    // create a private constructor
    private constructor() {
        lessonsArrayList = ArrayList()
        lessonsArrayList.add(Lesson(1,"Introduction to the Course", "6 min", "Here we learn JavaScript, starting from scratch and go on to advanced concepts like OOP. We concentrate on the language itself here, with the minimum of environment-specific notes.",false, "https://www.youtube.com/watch?v=1HakS7KsbCk", ""))
        lessonsArrayList.add(Lesson(2,"What is Javascript?", "6 min", "JavaScript was initially created to “make web pages alive”. The programs in this language are called scripts. They can be written right in a web page’s HTML and run automatically as the page loads. Scripts are provided and executed as plain text. They don’t need special preparation or compilation to run.",false, "https://www.youtube.com/watch?v=upDLs1sn7g4", ""))
        lessonsArrayList.add(Lesson(3,"Variables and conditionals", "7 min", "A variable is a “named storage” for data. We can use variables to store goodies, visitors, and other data. To create a variable in JavaScript, use the let keyword. Sometimes, we need to perform different actions based on different conditions. To do that, we can use the if statement and the conditional operator ?, that’s also called a “question mark” operator.",false,"https://www.youtube.com/watch?v=IsG4Xd6LlsM", ""))
        lessonsArrayList.add(Lesson(4,"Loops", "7 min", "We often need to repeat actions. For example, outputting goods from a list one after another or just running the same code for each number from 1 to 10. Loops are a way to repeat the same code multiple times.",false,"https://www.youtube.com/watch?v=s9wW2PpJsmQ", ""))
        lessonsArrayList.add(Lesson(5,"Functions", "6 min", "Quite often we need to perform a similar action in many places of the script. For example, we need to show a nice-looking message when a visitor logs in, logs out and maybe somewhere else. Functions are the main “building blocks” of the program. They allow the code to be called many times without repetition.",false,"https://www.youtube.com/watch?v=N8ap4k_1QEQ", ""))
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

    var selectedLessonPosition:Int? = null

    var username:String = ""


    // put any functions / operations you want to perform on data in the singleton here

    fun reset() {
        for(lesson in lessonsArrayList){
            lesson.isCompleted = false
            lesson.notes = ""
        }
    }

}