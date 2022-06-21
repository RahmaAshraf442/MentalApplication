package com.example.mental_health_app.di

import android.content.Context
import androidx.room.Room
import com.example.mental_health_app.database.PtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    // Room Database
    @Singleton
    @Provides
    fun getInstance(@ApplicationContext appContext: Context): PtDatabase {
        return Room.databaseBuilder(
            appContext,
            PtDatabase::class.java, "pt_database01",
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    }

}