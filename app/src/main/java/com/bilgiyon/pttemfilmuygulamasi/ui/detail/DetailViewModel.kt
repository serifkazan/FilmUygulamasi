package com.bilgiyon.pttemfilmuygulamasi.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults

class DetailViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: DetailRepository by lazy { DetailRepository(app.applicationContext) }

    fun insertMovie(movieResults: MovieResults?) = repository.insertMovie(movieResults)

    fun deleteMovie(movieResults: MovieResults?) = repository.deleteMovie(movieResults)

    fun getSingleMovie(movieId: Int?): LiveData<MovieResults> = repository.getSingleMovie(movieId)

    fun getAllMovies(): LiveData<List<MovieResults>> = repository.getAllMovies()
}