package com.example.notesapp.notes.di


import com.chaquo.python.Python
import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.NotesDao
import com.example.mental_health_app.database.dao.YogaDao
import com.example.notesapp.notes.model.RecommenderRepo
import com.example.notesapp.notes.model.YogaRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RecommenderModule {

    @Provides
    fun provideDao(): Python =
        Python.getInstance()

    @Provides
    fun providePython(python: Python) =
        RecommenderRepo(python)

}