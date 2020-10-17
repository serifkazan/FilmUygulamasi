package com.bilgiyon.pttemfilmuygulamasi.data.remote

import com.bilgiyon.pttemfilmuygulamasi.model.detail.MovieDetailResponse
import com.bilgiyon.pttemfilmuygulamasi.model.movie.MovieResponse
import com.bilgiyon.pttemfilmuygulamasi.model.reviews.MovieReviewResponse
import com.bilgiyon.pttemfilmuygulamasi.model.videos.MovieVideoResponse
import com.bilgiyon.pttemfilmuygulamasi.model.videos.MovieVideoResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") movieId: Int): Call<MovieDetailResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") movieId: Int): Call<MovieVideoResponse>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") movieId: Int): Call<MovieReviewResponse>
}