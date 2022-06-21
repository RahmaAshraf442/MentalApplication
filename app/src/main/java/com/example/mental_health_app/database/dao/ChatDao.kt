package com.example.mental_health_app.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mental_health_app.database.entities.MessageEntity

@Dao
interface ChatDao {


    @Query("SELECT * FROM MessageEntity")
    suspend fun getAllMessages(): List<MessageEntity>

    @Query("SELECT COUNT(id) FROM MessageEntity")
    fun getMessagesCount(): LiveData<Int>

    @Delete
    suspend fun deleteMessage(message: MessageEntity)

    @Query("DELETE FROM MessageEntity")
    suspend fun deleteAllMessages()

    @Insert()
    suspend fun addMessage(message: MessageEntity)
}
