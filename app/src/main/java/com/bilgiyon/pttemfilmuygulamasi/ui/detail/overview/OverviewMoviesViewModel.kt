package com.bilgiyon.pttemfilmuygulamasi.ui.detail.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bilgiyon.pttemfilmuygulamasi.model.detail.MovieDetailResponse
import com.bilgiyon.pttemfilmuygulamasi.model.videos.MovieVideoResults

class OverviewMoviesViewModel : ViewModel() {
    private val repository: OverviewRepository by lazy { OverviewRepository() }

    fun getDetails(movieId: Int): LiveData<MovieDetailResponse> = repository.getDetails(movieId)

    fun getVideos(movieId: Int): LiveData<List<MovieVideoResults>> =
        repository.getMovieVideos(movieId)
}