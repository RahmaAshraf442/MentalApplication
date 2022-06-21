package com.example.mental_health_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.notes.model.NoteModel

@Entity(tableName = "notesTable")

data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?=null,
    @ColumnInfo(name = "title") val noteTitle:String,
    @ColumnInfo(name = "description") val noteDescription:String,
    @ColumnInfo(name = "timestamp") val timeStamp:String)

fun NoteEntity.toNote(): NoteModel {
    return NoteModel(id ,noteTitle ,noteDescription , timeStamp)
}

fun NoteModel.toNoteEntity(): NoteEntity {
    return NoteEntity(id,title , desc , timeStamp)
}