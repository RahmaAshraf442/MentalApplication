package com.example.notesapp.notes.model

import com.example.mental_health_app.database.dao.NotesDao
import com.example.mental_health_app.database.entities.toNote
import com.example.mental_health_app.database.entities.toNoteEntity
import javax.inject.Inject

class NotesRepo @Inject constructor(private val dao: NotesDao) {

    fun getAllNotes(): List<NoteModel> {
        return dao.getAllNotes().map { it.toNote() }
    }


    suspend fun deleteNote(note: NoteModel){
        dao.deleteNote(note.toNoteEntity())
    }

    suspend fun insertNote(note: NoteModel){
        dao.insert(note.toNoteEntity())
    }

    suspend fun update(note: NoteModel){
        dao.updateNote(note.toNoteEntity())
    }
}
