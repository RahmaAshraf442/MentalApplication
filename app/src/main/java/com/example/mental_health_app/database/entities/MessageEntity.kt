package com.example.mental_health_app.database.entities

import androidx.room.*
import com.example.mental_health_app.chat.model.MessagesModel

@Entity
data class MessageEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long?=null,
    @ColumnInfo(name = "senderType") val senderType: Int,
    @ColumnInfo(name = "messageContent") val messageContent: String,
    @ColumnInfo(name = "messageTime") val messageTime: String
)
fun MessageEntity.toMessage(): MessagesModel {
    return MessagesModel(id ,senderType ,messageContent , messageTime)
}

fun MessagesModel.toMessageEntity(): MessageEntity {
    return MessageEntity(id ,messageType , content , messageTime)
}