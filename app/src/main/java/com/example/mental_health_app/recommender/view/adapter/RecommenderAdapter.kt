package com.example.mental_health_app.recommender.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mental_health_app.databinding.RecommenderItemBinding
import com.example.mental_health_app.databinding.YogaRowBinding
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.notesapp.notes.model.NoteModel

class RecommenderAdapter: RecyclerView.Adapter<RecommenderViewHolder>()  {

    private var booksList = mutableListOf<Array<Any>>()
    fun setBooks(book: Array<Array<Any>>) {
        booksList = book.toMutableList()
        notifyDataSetChanged()
    }
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommenderViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = RecommenderItemBinding.inflate(inflater, parent, false)
        return RecommenderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommenderViewHolder, position: Int) {
        val item = booksList[position]
        holder.binding.tvBookName.text= item[1].toString()
        holder.binding.tvAuthor.text= item[2].toString()
        holder.binding.tvDesc.text= item[9].toString()
    }

    override fun getItemCount(): Int {
        return booksList.size
    }
}


class RecommenderViewHolder(val binding: RecommenderItemBinding) : RecyclerView.ViewHolder(binding.root) {

}