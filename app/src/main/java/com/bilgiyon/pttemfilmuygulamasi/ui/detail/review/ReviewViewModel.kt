package com.bilgiyon.pttemfilmuygulamasi.ui.detail.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bilgiyon.pttemfilmuygulamasi.model.reviews.MovieReviewResult

class ReviewViewModel : ViewModel() {
    private val repository: ReviewRepository by lazy { ReviewRepository() }

    fun getReviews(movieId: Int): LiveData<List<MovieReviewResult>>? =
        repository.getReviews(movieId)
}