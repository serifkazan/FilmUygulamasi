package com.bilgiyon.pttemfilmuygulamasi.data

import com.bilgiyon.pttemfilmuygulamasi.data.MovieModel

data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    var results: List<MovieModel>
)