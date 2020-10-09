package com.bilgiyon.pttemfilmuygulamasi.api

import com.bilgiyon.pttemfilmuygulamasi.data.ConfigurationModel
import com.bilgiyon.pttemfilmuygulamasi.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("discover/movie")
    fun discoverMovies(
        @Query("page") page: Int,
        @Query("sort_by") sort_by: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieResponse>

    @GET("configuration")
    fun getConfigurations(
        @Query("api_key") api_key: String
    ): Call<ConfigurationModel>
}