package com.example.notesapp.notes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mental_health_app.databinding.ActivityNoteRvItemBinding
import com.example.notesapp.notes.model.NoteModel

class NotesRVAdapter( private val onDeleteClicked: (NoteModel) -> Unit,
                     private val onItemClicked: (NoteModel) -> Unit )
    : RecyclerView.Adapter<NotesViewHolder>() {

    private var allNotes = mutableListOf<NoteModel>()
    fun setNotes(notes: List<NoteModel>) {
        allNotes = notes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityNoteRvItemBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val note = allNotes[position]
        holder.binding.idTVNote.text = note.title
        holder.binding.idTVDate.text = "Last Updated : " + note.timeStamp

        holder.binding.idIVDelete.setOnClickListener{
            onDeleteClicked(note)
        }
        holder.itemView.setOnClickListener{
            onItemClicked(note)
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

}

class NotesViewHolder(val binding: ActivityNoteRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

}