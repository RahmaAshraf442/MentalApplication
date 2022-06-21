package com.example.notesapp.notes.model

import com.chaquo.python.Python
import com.example.mental_health_app.database.dao.YogaDao
import com.example.mental_health_app.database.entities.*
import com.example.mental_health_app.yoga.model.YogaModel
import javax.inject.Inject

class RecommenderRepo @Inject constructor(private var python: Python) {

    fun getRandom(): Array<Array<Any>> {

        python = Python.getInstance()
        val pythonFile = python.getModule("functions")
        return pythonFile.callAttr("Random_Recommendations").toJava(Array<Array<Any>>::class.java)
        //return pythonFile.callAttr("mai2n",2).toJava(ArrayList<String>)

    }
    fun getRecommend(): Array<Array<Any>> {

        python = Python.getInstance()
        val pythonFile = python.getModule("functions")
        return pythonFile.callAttr("Best_recommendations").toJava(Array<Array<Any>>::class.java)
        //return pythonFile.callAttr("mai2n",2).toJava(ArrayList<String>)

    }
    fun getBest(): Array<Array<Any>> {

        python = Python.getInstance()
        val pythonFile = python.getModule("functions")
        return pythonFile.callAttr("Best_recommendations").toJava(Array<Array<Any>>::class.java)
        //return pythonFile.callAttr("mai2n",2).toJava(ArrayList<String>)

    }

    fun getSearch(searchInput: String): Array<Array<Any>> {
        python = Python.getInstance()
        val pythonFile = python.getModule("functions")
        return pythonFile.callAttr("Search",searchInput).toJava(Array<Array<Any>>::class.java)
        //return pythonFile.callAttr("mai2n",2).toJava(ArrayList<String>)

    }
}
