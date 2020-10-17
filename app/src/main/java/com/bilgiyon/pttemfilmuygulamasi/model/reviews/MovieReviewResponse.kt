package com.bilgiyon.pttemfilmuygulamasi.model.reviews

import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<MovieReviewResult>,
    @SerializedName("total_pages")
    var total_pages: Int,
    @SerializedName("total_results")
    var total_results: Int
)