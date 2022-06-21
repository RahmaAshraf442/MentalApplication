package com.example.notesapp.notes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mental_health_app.databinding.ActivityNoteBinding
import com.example.notesapp.notes.model.NoteModel
import com.example.notesapp.notes.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {

    private val viewModal: NotesViewModel by viewModels()
    private lateinit var binding: ActivityNoteBinding
    private val noteRVAdapter = NotesRVAdapter({deleteNote(it)},{itemClicked(it)} )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteBinding.inflate(layoutInflater)
        initRV()
        viewModal.getAllNotes()
        observeViewModel()
        onClickActions()
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.notesRV.layoutManager = linearLayoutManager
        val view: View = binding.root
        Toast.makeText(this, "${viewModal.notesList!!.size} items found",
            Toast.LENGTH_LONG).show()
        setContentView(view)
    }

    private fun initRV(){
        binding.notesRV.adapter =noteRVAdapter
    }


    private fun observeViewModel() {
        viewModal.allNotes.observe(this) {
            noteRVAdapter.setNotes(it)
        }
    }

    private fun deleteNote(note: NoteModel){
        viewModal.deleteNote(note)
    }

    private fun itemClicked(note: NoteModel){
        val intent = Intent(this@NotesActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.desc)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    private fun onClickActions() {
        binding.fabAddNewNote.setOnClickListener{
            val intent = Intent(this@NotesActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


}