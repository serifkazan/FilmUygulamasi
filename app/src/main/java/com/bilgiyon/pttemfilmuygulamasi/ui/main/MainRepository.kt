package com.bilgiyon.pttemfilmuygulamasi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bilgiyon.pttemfilmuygulamasi.data.remote.ApiClient
import com.bilgiyon.pttemfilmuygulamasi.data.remote.ApiService
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResponse
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    private val apiService: ApiService by lazy { ApiClient.getClient() }

    fun getPopularMovies(): LiveData<List<MovieResults>>? {
        val moviesListData: MutableLiveData<List<MovieResults>> = MutableLiveData()
        apiService.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getPopularMovies", t.message.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                moviesListData.value = response.body()?.results
            }

        })

        return moviesListData
    }

    fun getTopRatedMovies(): LiveData<List<MovieResults>>? {
        val moviesListData: MutableLiveData<List<MovieResults>> = MutableLiveData()
        apiService.getTopRatedMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("getPopularMovies", t.message.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                moviesListData.value = response.body()?.results
            }

        })
        return moviesListData
    }
}