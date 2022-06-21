package com.example.notesapp.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.notes.model.NotesRepo
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.journal.model.JournalImagesModel
import com.example.notesapp.notes.model.JournalImagesRepo
import com.example.notesapp.notes.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalImagesViewModel @Inject constructor(private val repo:JournalImagesRepo) : BaseViewModel(){


    val allJournalImages = MutableLiveData<List<JournalImagesModel>>()
    var journalImagesList:List<JournalImagesModel>?=null

    fun getAllJournalImage(){
        viewModelScope.launch {
            allJournalImages.value=   repo.getAllJournalImages()
            journalImagesList=repo.getAllJournalImages()
        }
    }

    fun deleteJournalImage(image: JournalImagesModel){
        viewModelScope.launch(Dispatchers.IO)  {
            repo.deleteJournalImage(image)
        }

    }
    fun addJournalImage(image: JournalImagesModel){
        viewModelScope.launch {
            repo.insertJournalImage(image)
        }
    }
}
