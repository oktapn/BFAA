package com.okta.moviecatalogue.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okta.moviecatalogue.model.movielist.ResponseMovie
import com.okta.moviecatalogue.model.movielist.ResultListMovie
import com.okta.moviecatalogue.networking.NetworkError
import com.okta.moviecatalogue.networking.Service
import rx.subscriptions.CompositeSubscription


class MovieViewModel : ViewModel() {
    private val listMovies = MutableLiveData<List<ResultListMovie>>()

    private val subscriptions = CompositeSubscription()

    internal val movies: LiveData<List<ResultListMovie>>
        get() = listMovies

    internal fun setMovies(apiKey: String, language: String, service: Service){
        val subscription = service.getMovie( object : Service.GetMovieCallback{
            override fun onSuccess(response: ResponseMovie) {
                if (response.resultListMovies.isNotEmpty()){
                    listMovies.postValue(response.resultListMovies)
                    Log.e("TAGSS","isnotempty")
                }
                Log.e("TAGSS","isempty")
            }

            override fun onError(networkError: NetworkError) {
                Log.d("Failure", networkError.appErrorMessage)
            }

        },apiKey, language)

        subscriptions.add(subscription)
    }
}
