package com.example.notesapp.notes.di


import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.JournalImagesDao
import com.example.notesapp.notes.model.JournalImagesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class JournalImagesModule {

    @Provides
    fun provideDao(db: PtDatabase): JournalImagesDao =
        db.journalImagesDao()

    @Provides
    fun provideRepo(dao: JournalImagesDao) =
        JournalImagesRepo(dao)

}