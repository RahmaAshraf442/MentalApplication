package com.example.mental_health_app.chat.di

import com.example.mental_health_app.chat.model.repo.ChatRepo
import com.example.mental_health_app.database.PtDatabase
import com.example.mental_health_app.database.dao.ChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MessagesModule {

    @Singleton
    @Provides
    fun provideDao(db: PtDatabase): ChatDao =
        db.messagesDao()

    @Singleton
    @Provides
    fun provideRepo(dao: ChatDao) =
        ChatRepo(dao)
}