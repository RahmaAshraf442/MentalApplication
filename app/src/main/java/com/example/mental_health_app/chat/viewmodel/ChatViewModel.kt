package com.example.mental_health_app.chat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.chat.model.MessagesModel
import com.example.mental_health_app.chat.model.repo.ChatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repo:ChatRepo) : BaseViewModel(){

    //todo flow
    val chatMessages = MutableLiveData<List<MessagesModel>>()
    var chatList:List<MessagesModel>?=null

    fun getAllMessages(){

        viewModelScope.launch {
            chatMessages.value=   repo.getChatMessages()
            chatList=repo.getChatMessages()
        }

    }

    fun deleteMessage(message: MessagesModel){
        //todo transaction room ( )
        viewModelScope.launch {
            repo.deleteMessage(message)
            getAllMessages()
        }
    }

    fun deleteAllMessages(){
        viewModelScope.launch {
            repo.deleteAllMessages()
            getAllMessages()
        }
    }
    fun addMessage(message: MessagesModel){
        viewModelScope.launch {
            repo.insertMessage(message)
            getAllMessages()
        }
    }
}