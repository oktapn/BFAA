package com.okta.moviecatalogue.model.tvshowlist


import com.google.gson.annotations.SerializedName

data class ResponseTvShow(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultListTvShows: ArrayList<ResultListTvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)