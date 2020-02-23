package com.okta.moviecatalogue.model.movielist


import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultListMovies: List<ResultListMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)