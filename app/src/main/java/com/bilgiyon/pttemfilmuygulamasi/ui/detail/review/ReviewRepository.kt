package com.bilgiyon.pttemfilmuygulamasi.ui.detail.review

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilgiyon.pttemfilmuygulamasi.data.remote.ApiClient
import com.bilgiyon.pttemfilmuygulamasi.data.remote.ApiService
import com.bilgiyon.pttemfilmuygulamasi.model.reviews.MovieReviewResponse
import com.bilgiyon.pttemfilmuygulamasi.model.reviews.MovieReviewResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewRepository {
    private val apiService: ApiService by lazy { ApiClient.getClient() }

    fun getReviews(movieId: Int): MutableLiveData<List<MovieReviewResult>> {
        val movieDetailLive: MutableLiveData<List<MovieReviewResult>> = MutableLiveData()
        apiService.getMovieReviews(movieId).enqueue(object : Callback<MovieReviewResponse> {
            override fun onFailure(call: Call<MovieReviewResponse>, t: Throwable) {
                Log.e("getDetails", t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieReviewResponse>,
                response: Response<MovieReviewResponse>
            ) {
                movieDetailLive.value = response.body()?.results
            }

        })

        return movieDetailLive
    }
}