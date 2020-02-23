package com.okta.moviecatalogue.networking

import com.okta.moviecatalogue.model.movielist.ResponseMovie
import com.okta.moviecatalogue.model.tvshowlist.ResponseTvShow
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface NetworkService {
    @GET("movie")
    fun getMovie(@Query("api_key") apiKey: String,
                 @Query("language") language: String): Observable<ResponseMovie>

    @GET("tv")
    fun getTvShow(@Query("api_key") apiKey: String,
                  @Query("language") language: String): Observable<ResponseTvShow>

}
