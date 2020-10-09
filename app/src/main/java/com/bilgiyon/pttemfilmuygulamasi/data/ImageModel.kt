package com.bilgiyon.pttemfilmuygulamasi.data

data class ImageModel(
    val base_url: String,
    val secure_base_url: String,
    val backdrop_sizes: List<String>,
    val logo_sizes: List<String>,
    val poster_sizes: List<String>,
    val still_sizes: List<String>
) {
}