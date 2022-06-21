package com.example.mental_health_app.base_classes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val networkErrorLD = MutableLiveData<Boolean>()
    val serverErrorLD = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

}