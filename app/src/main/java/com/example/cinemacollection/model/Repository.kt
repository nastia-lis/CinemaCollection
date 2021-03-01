package com.example.cinemacollection.model

interface Repository {
    fun getMovieFromServer(): Cinema
}