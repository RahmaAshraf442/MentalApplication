package com.example.mental_health_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mental_health_app.journal.model.JournalImagesModel
import com.example.notesapp.notes.model.NoteModel

@Entity(tableName = "journalImagesTable")

data class JournalImagesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "journalImage") val journalImage:Int)

fun JournalImagesEntity.toJournalImage(): JournalImagesModel {
    return JournalImagesModel(id ,journalImage )
}

fun JournalImagesModel.toJournalImagesEntity(): JournalImagesEntity {
    return JournalImagesEntity(id,journalImage )
}