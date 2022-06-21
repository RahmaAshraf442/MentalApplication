package com.example.mental_health_app.usage.di

import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.NotesDao
import com.example.mental_health_app.database.dao.UsageDao
import com.example.mental_health_app.usage.model.UsageRepo
import com.example.notesapp.notes.model.NotesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsageModule {

    @Provides
    fun provideDao(db: PtDatabase): UsageDao =
        db.usageDao()

    @Provides
    fun provideRepo(dao: UsageDao) =
        UsageRepo(dao)

}