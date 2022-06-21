package com.example.mental_health_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mental_health_app.database.dao.*
import com.example.mental_health_app.database.entities.*

@Database(entities = [MessageEntity::class , NoteEntity::class, YogaEntity::class, UsageEntity::class,
    JournalImagesEntity::class, JournalViewedImagesEntity::class],
    version = 1, exportSchema = false)
abstract class PtDatabase : RoomDatabase(){

    abstract fun messagesDao(): ChatDao
    abstract fun notesDao(): NotesDao
    abstract fun yogaDao(): YogaDao
    abstract fun usageDao(): UsageDao
    abstract fun journalImagesDao(): JournalImagesDao
    abstract fun journalViewedImagesDao(): JournalViewedImagesDao
}