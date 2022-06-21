package com.example.notesapp.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.notesapp.notes.model.NotesRepo
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.notesapp.notes.model.NoteModel
import com.example.notesapp.notes.model.RecommenderRepo
import com.example.notesapp.notes.model.YogaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommenderViewModel @Inject constructor(private val repo:RecommenderRepo) : BaseViewModel(){


    val allRecommended = MutableLiveData<Array<Array<Any>>>()
    var recommendedList:Array<Array<Any>>?=null

    val allRandom = MutableLiveData<Array<Array<Any>>>()
    var randomList:Array<Array<Any>>?=null

    val allBest = MutableLiveData<Array<Array<Any>>>()
    var bestList:Array<Array<Any>>?=null

    val allSearch = MutableLiveData<Array<Array<Any>>>()
    var searchList:Array<Array<Any>>?=null

    fun getAllRecommended(){
        viewModelScope.launch {
            allRecommended.value=   repo.getRecommend()
            recommendedList=repo.getRecommend()
        }
    }

    fun getAllRandom(){
        viewModelScope.launch {
            allRandom.value=   repo.getRandom()
            randomList=repo.getRandom()
        }
    }

    fun getAllBest(){
        viewModelScope.launch {
            allBest.value=   repo.getBest()
            bestList=repo.getBest()
        }
    }

    fun getAllSearch(searchInput: String){
        viewModelScope.launch {
            allSearch.value=   repo.getSearch(searchInput)
            searchList=repo.getSearch(searchInput)
        }
    }

}
