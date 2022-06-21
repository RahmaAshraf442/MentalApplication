package com.example.mental_health_app.database.dao

import androidx.room.*
import com.example.mental_health_app.database.entities.NoteEntity


@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): List<NoteEntity>

    @Update
    suspend fun updateNote(note: NoteEntity)
}