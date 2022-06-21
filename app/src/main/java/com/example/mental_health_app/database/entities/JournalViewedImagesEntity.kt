package com.example.mental_health_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mental_health_app.journal.model.JournalImagesModel
import com.example.notesapp.notes.model.NoteModel

@Entity(tableName = "journalViewedImagesTable")

data class JournalViewedImagesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "journalImage") val journalImage:Int)

fun JournalViewedImagesEntity.toJournalImage(): JournalImagesModel {
    return JournalImagesModel(id ,journalImage )
}

fun JournalImagesModel.toViewedJournalImagesEntity(): JournalViewedImagesEntity {
    return JournalViewedImagesEntity(id,journalImage )
}