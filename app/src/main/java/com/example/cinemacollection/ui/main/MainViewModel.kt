package com.example.cinemacollection.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val myLiveData: MutableLiveData<Any> = MutableLiveData()) : ViewModel() {

    fun getLiveData(): LiveData<Any> {
        getMyDataFromLocal()
        return myLiveData
    }

    private fun getMyDataFromLocal() {
        Thread {
            sleep(1000)
            myLiveData.postValue(Any())
        }.start()
    }
}