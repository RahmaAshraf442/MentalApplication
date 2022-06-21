package com.example.notesapp.notes.model

import com.example.mental_health_app.database.dao.YogaDao
import com.example.mental_health_app.database.entities.*
import com.example.mental_health_app.yoga.model.YogaModel
import javax.inject.Inject

class YogaDetailsRepo @Inject constructor(private val dao: YogaDao) {

    fun getYogaDetails(id : Int): YogaModel {
        return dao.getYogaDetails(id).toYoga()
    }
}
