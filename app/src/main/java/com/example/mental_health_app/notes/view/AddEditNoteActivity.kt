package com.example.notesapp.notes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mental_health_app.databinding.ActivityAddEditNoteBinding
import com.example.notesapp.notes.model.NoteModel
import com.example.notesapp.notes.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddEditNoteActivity : AppCompatActivity() {

    private  val viewModel: NotesViewModel by viewModels()
    private lateinit var binding: ActivityAddEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {

            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            binding.btnSave.text = "Update Note"
            binding.etNoteTitle.setText(noteTitle)
            binding.etNoteDesc.setText(noteDescription)
        }
        else {
            binding.btnSave.text = "Save Note"
        }

        binding.btnSave.setOnClickListener {
            val noteTitle = binding.etNoteTitle.text.toString()
            val noteDescription = binding.etNoteDesc.text.toString()
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDateAndTime: String = sdf.format(Date())

            if (noteType.equals("Edit")) {
                val noteId =intent.getLongExtra("noteId" , 0)
                if (noteTitle.isNotEmpty()) {
                    val updatedNote = NoteModel(noteId ,noteTitle, noteDescription, currentDateAndTime)
                    viewModel.updateNote(updatedNote)
                }
            }
            else {

                if (noteTitle.isNotEmpty()) {

                    viewModel.addNote(NoteModel(null ,noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(applicationContext, NotesActivity::class.java))
            this.finish()
        }

        val view: View = binding.root
        setContentView(view)
    }

    override fun onBackPressed(){
        startActivity(Intent(applicationContext, NotesActivity::class.java))
        this.finish()
    }
}
