package com.example.notesapp.notes.di


import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.NotesDao
import com.example.notesapp.notes.model.NotesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NotesModule {

    @Provides
    fun provideDao(db: PtDatabase): NotesDao =
        db.notesDao()

    @Provides
    fun provideRepo(dao: NotesDao) =
        NotesRepo(dao)

}