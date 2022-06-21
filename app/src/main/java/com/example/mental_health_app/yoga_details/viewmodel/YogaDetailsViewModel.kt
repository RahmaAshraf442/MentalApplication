package com.example.notesapp.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.notes.model.NotesRepo
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.notesapp.notes.model.NoteModel
import com.example.notesapp.notes.model.YogaDetailsRepo
import com.example.notesapp.notes.model.YogaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YogaDetailsViewModel @Inject constructor(private val repo:YogaDetailsRepo) : BaseViewModel(){

    val yoga = MutableLiveData<YogaModel>()
    var yogaDetails:YogaModel?=null

    fun getYogaDetails(id: Int){
        viewModelScope.launch {
            yoga.value=repo.getYogaDetails(id)
            yogaDetails = repo.getYogaDetails(id)
        }
    }
}
