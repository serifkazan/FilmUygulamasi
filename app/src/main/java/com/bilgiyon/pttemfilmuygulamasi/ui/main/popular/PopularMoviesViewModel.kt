package com.bilgiyon.pttemfilmuygulamasi.ui.main.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import com.bilgiyon.pttemfilmuygulamasi.ui.main.MainRepository

class PopularMoviesViewModel : ViewModel() {
    private val repository: MainRepository by lazy { MainRepository() }

    fun getPopularMovies(): LiveData<List<MovieResults>>? = repository.getPopularMovies()
}