package com.example.notesapp.notes.di


import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.YogaDao
import com.example.notesapp.notes.model.YogaRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class YogaModule {

    @Provides
    fun provideDao(db: PtDatabase): YogaDao =
        db.yogaDao()

    @Provides
    fun provideRepo(dao: YogaDao) =
        YogaRepo(dao)

}