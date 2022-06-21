package com.example.notesapp.notes.di


import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.JournalViewedImagesDao
import com.example.notesapp.notes.model.JournalViewedImagesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class JournalViewedImagesModule {

    @Provides
    fun provideDao(db: PtDatabase): JournalViewedImagesDao =
        db.journalViewedImagesDao()

    @Provides
    fun provideRepo(dao: JournalViewedImagesDao) =
        JournalViewedImagesRepo(dao)

}