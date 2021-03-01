package com.example.cinemacollection.viewmodel

import com.example.cinemacollection.model.Cinema

sealed class AppState {
    data class Success(val dataCinema: Cinema) : AppState()
    data class Error(val exception: Throwable) : AppState()
    object Loading : AppState()
}