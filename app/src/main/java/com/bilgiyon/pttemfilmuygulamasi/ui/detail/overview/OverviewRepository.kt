package com.bilgiyon.pttemfilmuygulamasi.ui.detail.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bilgiyon.pttemfilmuygulamasi.data.remote.ApiClient
import com.bilgiyon.pttemfilmuygulamasi.data.remote.ApiService
import com.bilgiyon.pttemfilmuygulamasi.model.detail.MovieDetailResponse
import com.bilgiyon.pttemfilmuygulamasi.model.videos.MovieVideoResponse
import com.bilgiyon.pttemfilmuygulamasi.model.videos.MovieVideoResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewRepository {
    private val apiService: ApiService by lazy { ApiClient.getClient() }

    fun getDetails(movieId: Int): MutableLiveData<MovieDetailResponse> {
        val movieDetailLive: MutableLiveData<MovieDetailResponse> = MutableLiveData()
        apiService.getMovieDetail(movieId).enqueue(object : Callback<MovieDetailResponse> {
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("getDetails", t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                movieDetailLive.value = response.body()
            }

        })

        return movieDetailLive
    }

    fun getMovieVideos(movieId: Int): MutableLiveData<List<MovieVideoResults>> {
        val movieVideoLive: MutableLiveData<List<MovieVideoResults>> = MutableLiveData()
        apiService.getMovieVideos(movieId).enqueue(object : Callback<MovieVideoResponse> {
            override fun onFailure(call: Call<MovieVideoResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }

            override fun onResponse(
                call: Call<MovieVideoResponse>,
                response: Response<MovieVideoResponse>
            ) {
                movieVideoLive.value = response.body()?.results
            }

        })
        return movieVideoLive
    }
}