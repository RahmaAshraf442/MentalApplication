package com.example.notesapp.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.notes.model.NotesRepo
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.database.dao.JournalViewedImagesDao
import com.example.mental_health_app.journal.model.JournalImagesModel
import com.example.notesapp.notes.model.JournalImagesRepo
import com.example.notesapp.notes.model.JournalViewedImagesRepo
import com.example.notesapp.notes.model.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalViewredImagesViewModel @Inject constructor(private val repo:JournalViewedImagesRepo) : BaseViewModel(){


    val allViewedJournalImages = MutableLiveData<List<JournalImagesModel>>()
    var viewedJournalImagesList:List<JournalImagesModel>?=null

    fun getAllViewedJournalImage(){
        viewModelScope.launch {
            allViewedJournalImages.value=   repo.getAllViewedJournalImages()
            viewedJournalImagesList=repo.getAllViewedJournalImages()
        }
    }

    fun deleteAllViewedJournalImage(){
        viewModelScope.launch(Dispatchers.IO)  {
            repo.deleteAllViewedJournalImage()
        }

    }
    fun addViewedJournalImage(image: JournalImagesModel){
        viewModelScope.launch {
            repo.insertViewedJournalImage(image)
        }
    }
}
