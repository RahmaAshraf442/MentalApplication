package com.example.notesapp.notes.model

import com.example.mental_health_app.database.dao.YogaDao
import com.example.mental_health_app.database.entities.*
import com.example.mental_health_app.yoga.model.YogaModel
import javax.inject.Inject

class YogaRepo @Inject constructor(private val dao: YogaDao) {

    fun getAllYoga(): List<YogaModel> {
        return dao.getAllYoga().map { it.toYoga() }
    }



    suspend fun insertYoga(yoga: YogaModel){
        dao.insert(yoga.toYogaEntity())
    }
}
