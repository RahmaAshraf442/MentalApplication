package com.example.mental_health_app.usage.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.mental_health_app.base_classes.BaseViewModel
import com.example.mental_health_app.database.entities.UsageEntity
import com.example.mental_health_app.usage.model.UsageModel
import com.example.mental_health_app.usage.model.UsageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsageViewModel @Inject constructor(private val repo:UsageRepo) :BaseViewModel() {

    val allUsage = MutableLiveData<List<UsageModel>>()
    var usageList:List<UsageModel>?=null
    private val _secondsUpTime = MutableLiveData<Int>()
    val secondsUpTime: LiveData<Int> = _secondsUpTime

    private var startTime = -1L
    private var counterJob: Job? = null

    fun getAllUsage(){
        viewModelScope.launch {
            allUsage.value=   repo.getAllUsage()
            usageList=repo.getAllUsage()
        }
    }

    fun addUsage(usage: UsageModel){
        viewModelScope.launch {
            repo.insertUsage(usage)
        }
    }

    fun notifyAppStarted() {
        startTime = System.currentTimeMillis()
        viewModelScope.launch {
                val totalTime = System.currentTimeMillis() - startTime
                _secondsUpTime.value = (totalTime / 1000).toInt()
                Log.e("Start", _secondsUpTime.value .toString() + "  " + totalTime.toString())
        }
    }

    fun notifyAppStopped(): Int{
        if (startTime < 0) {
            Log.e("TAG", "Tried to notify app stopped without notifying app started first.")
            return 0
        }
        counterJob?.cancel()
            val totalTime = System.currentTimeMillis() - startTime
            Log.e("Stop223", totalTime.toInt().toString())
        return totalTime.toInt()
    }
}