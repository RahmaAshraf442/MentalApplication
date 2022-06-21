package com.example.mental_health_app.chat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mental_health_app.R
import com.example.mental_health_app.chat.model.MessagesModel
import kotlinx.android.synthetic.main.bot_msg_item.view.*
import kotlinx.android.synthetic.main.user_msg_item.view.*


private var messagesList= mutableListOf<MessagesModel>()

class ChatAdapter(val context: Context ,private val onItemClicked: (MessagesModel) -> Unit) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setMessages(messages: List<MessagesModel>) {
        messagesList = messages.toMutableList()
        notifyDataSetChanged()
    }

    @Override
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return if (viewType == MESSAGE_TYPE_Mine) {
            MyMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.user_msg_item, parent, false)
            )
        } else BotMessageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.bot_msg_item, parent, false)
        )
    }

    //binding
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messagesList[position]
        when (message.messageType) {
            MESSAGE_TYPE_Mine -> (holder as MyMessageViewHolder).bind(position)
            MESSAGE_TYPE_Bot -> (holder as BotMessageViewHolder).bind(position)
        }

        holder.itemView.setOnClickListener{
            onItemClicked(message)
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return messagesList[position].messageType
    }

    companion object{ const val MESSAGE_TYPE_Mine = 0
            const val MESSAGE_TYPE_Bot = 1}

    //My message viewHolder
    private class MyMessageViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            var message: MessagesModel = messagesList[position]
            itemView.tv_user_message.text = message.content
            itemView.tv_user_message_time.text=message.messageTime
        }
    }

    //Bot message viewHolder
    private class BotMessageViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(position: Int) {
            var message: MessagesModel = messagesList[position]
            itemView.tv_bot_message.text = message.content
            itemView.tv_bot_message_time.text=message.messageTime
        }
    }
}

