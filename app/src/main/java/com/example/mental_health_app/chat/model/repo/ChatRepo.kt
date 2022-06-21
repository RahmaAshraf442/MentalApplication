package com.example.mental_health_app.chat.model.repo

import com.example.mental_health_app.chat.model.MessagesModel
import com.example.mental_health_app.database.dao.ChatDao
import com.example.mental_health_app.database.entities.MessageEntity
import com.example.mental_health_app.database.entities.toMessage
import com.example.mental_health_app.database.entities.toMessageEntity
import javax.inject.Inject

class ChatRepo @Inject constructor(private val dao: ChatDao) {


    suspend fun getChatMessages(): List<MessagesModel> {
        return dao.getAllMessages().map { it.toMessage() }
    }

    suspend fun deleteMessage(message: MessagesModel) {
        dao.deleteMessage(message.toMessageEntity())
    }

    suspend fun deleteAllMessages() {
        dao.deleteAllMessages()
    }


    // todo insert function
    suspend fun insertMessage(message: MessagesModel){
        var messageEntity: MessageEntity = message.toMessageEntity()
        dao.addMessage(messageEntity)
    }
}