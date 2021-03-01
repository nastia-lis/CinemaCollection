package com.example.cinemacollection.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemacollection.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val myLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl
) :
    ViewModel() {

    fun getLiveData() = myLiveData

    fun getCinemaData() {
        getMyDataFromLocal()
    }

    private fun getMyDataFromLocal() {
        myLiveData.value = AppState.Loading
        Thread {
            sleep(1000)
            myLiveData.postValue(AppState.Success(repositoryImpl.getMovieFromServer()))
        }.start()
    }
}