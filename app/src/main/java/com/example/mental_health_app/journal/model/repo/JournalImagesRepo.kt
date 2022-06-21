package com.example.notesapp.notes.model

import com.example.mental_health_app.database.dao.JournalImagesDao
import com.example.mental_health_app.database.entities.toJournalImage
import com.example.mental_health_app.database.entities.toJournalImagesEntity
import com.example.mental_health_app.journal.model.JournalImagesModel
import javax.inject.Inject

class JournalImagesRepo @Inject constructor(private val dao: JournalImagesDao) {

    fun getAllJournalImages(): List<JournalImagesModel> {
        return dao.getAllJournalImages().map { it.toJournalImage() }
    }


    suspend fun deleteJournalImage(image: JournalImagesModel){
        dao.deleteJournalImage(image.toJournalImagesEntity())
    }

    suspend fun insertJournalImage(image: JournalImagesModel){
        dao.insertJournalImage(image.toJournalImagesEntity())
    }

}
