package com.example.notesapp.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.notes.model.NotesRepo
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.notesapp.notes.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repo:NotesRepo) : BaseViewModel(){


    val allNotes = MutableLiveData<List<NoteModel>>()
    var notesList:List<NoteModel>?=null

    fun getAllNotes(){
        viewModelScope.launch {
            allNotes.value=   repo.getAllNotes()
            notesList=repo.getAllNotes()
        }
    }

    fun deleteNote(note: NoteModel){
        viewModelScope.launch(Dispatchers.IO)  {
            repo.deleteNote(note)
            getAllNotes()
        }

    }

    fun updateNote(note: NoteModel){
        viewModelScope.launch(Dispatchers.IO)  {
            repo.update(note)
        }
    }

    fun addNote(note: NoteModel){
        viewModelScope.launch {
            repo.insertNote(note)
        }
    }
}
