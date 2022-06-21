package com.example.notesapp.notes.model

import com.example.mental_health_app.database.dao.JournalViewedImagesDao
import com.example.mental_health_app.database.entities.toJournalImage
import com.example.mental_health_app.database.entities.toViewedJournalImagesEntity
import com.example.mental_health_app.journal.model.JournalImagesModel
import javax.inject.Inject

class JournalViewedImagesRepo @Inject constructor(private val dao: JournalViewedImagesDao) {

    fun getAllViewedJournalImages(): List<JournalImagesModel> {
        return dao.getAllViewedJournalImages().map { it.toJournalImage() }
    }


    fun deleteAllViewedJournalImage(){
        dao.deleteViewedJournalImage()
    }

    suspend fun insertViewedJournalImage(image: JournalImagesModel){
        dao.insertViewedJournalImage(image.toViewedJournalImagesEntity())
    }

}
