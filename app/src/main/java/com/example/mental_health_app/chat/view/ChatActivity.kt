package com.example.mental_health_app.chat.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.mental_health_app.R
import com.example.mental_health_app.chat.viewmodel.ChatViewModel
import com.example.mental_health_app.R.id.clear_chat
import com.example.mental_health_app.chat.model.MessagesModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_chat.*
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.*

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter :ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        adapter = ChatAdapter(this){ deleteMessage(it) }
        initRV()
        viewModel.getAllMessages()
        observeViewModel()
        onClickEvents()
    }

    override fun onStart() {
        super.onStart()
        scrollToBottom()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //this adds items to the action bar
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId
        if (id == clear_chat) {
            viewModel.deleteAllMessages()
            return true }
        return super.onOptionsItemSelected(item)
    }


    private fun scrollToBottom(){
        //In case there are messages, scroll to bottom
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_chat.scrollToPosition(adapter.itemCount - 1) }
        }
    }


    private fun observeViewModel() {
        viewModel.chatMessages.observe(this) {
            adapter.setMessages(it)
        }
    }


    private fun initRV() {
        rv_chat.layoutManager = LinearLayoutManager(this);
        rv_chat.adapter = adapter
    }

    private fun sendUserMessage(): String {
        val message = et_message.text.toString()
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this));
        }
        val python = Python.getInstance()
        val pythonFile = python.getModule("chat")
        return pythonFile.callAttr("main",message).toString()
    }

    private fun addMessage() {
        val message = et_message.text.toString()
        val messageModel =MessagesModel(null ,0 , message , getCurrentTime())
        if (message.isNotEmpty()) {
            et_message.setText("")
            viewModel.addMessage(messageModel)
            if(message == "what is depression?") {
                viewModel.addMessage(MessagesModel(null, 1,
                    "Comorbidity with depression is in older adults", getCurrentTime()))
            }
            else if(message == "what is anxiety"){
                viewModel.addMessage(MessagesModel(null, 1,
                    "Development and Course Median age at onset of social anxiety disorder" +
                            " in the United States is 13 years.", getCurrentTime()))
            }
            scrollToBottom()
        }
    }


    private fun onClickEvents() {
        //Send a message
        btn_send.setOnClickListener {
            addMessage()
        }
        et_message.setOnClickListener{
            scrollToBottom() }
    }


    private fun getCurrentTime(): String{
        val simpleDateFormat = SimpleDateFormat("h:mm a")
        val currentTime: String = simpleDateFormat.format(Date())
        Log.v("Mine" , currentTime)
        return currentTime }


    private fun deleteMessage(message: MessagesModel){
        viewModel.deleteMessage(message)
    }

}
