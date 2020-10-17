package com.bilgiyon.pttemfilmuygulamasi.ui.main.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.ui.main.MainRepository

class TopRatedViewModel : ViewModel() {
    private val repository: MainRepository by lazy { MainRepository() }

    fun getTopRatedMovies(): LiveData<List<MovieResults>>? = repository.getTopRatedMovies()
}