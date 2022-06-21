package com.example.notesapp.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.notes.model.NotesRepo
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.notesapp.notes.model.NoteModel
import com.example.notesapp.notes.model.YogaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YogaViewModel @Inject constructor(private val repo:YogaRepo) : BaseViewModel(){


    val allYoga = MutableLiveData<List<YogaModel>>()
    var yogaList:List<YogaModel>?=null

    fun getAllYoga(){
        viewModelScope.launch {
            allYoga.value=   repo.getAllYoga()
            yogaList=repo.getAllYoga()
        }
    }

    fun addYoga(yoga: YogaModel){
        viewModelScope.launch {
            repo.insertYoga(yoga)
        }
    }
}
