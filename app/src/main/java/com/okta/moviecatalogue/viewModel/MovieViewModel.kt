package com.okta.moviecatalogue.viewModel

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
//    private var listTvShow = MutableLiveData<List<ResultListTvShow>>()

    private val subscriptions = CompositeSubscription()

    internal val movies: LiveData<List<ResultListMovie>>
        get() = listMovies

//    internal val tvShow: LiveData<List<ResultListTvShow>>
//        get() = listTvShow

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

//    internal fun setTvshows(apiKey: String, language: String, service: Service){
//        val subscription = service.getTvShow( object : Service.GetTvshowCallback{
//            override fun onSuccess(response: ResponseTvShow) {
//                if (response.resultListTvShows.isNotEmpty()){
//                    listTvShow.postValue(response.resultListTvShows)
//                    Log.e("TAGSS","isnotempty")
//                }
//                Log.e("TAGSS","isempty")
//            }
//
//            override fun onError(networkError: NetworkError) {
//                Log.d("Failure", networkError.appErrorMessage)
//            }
//
//        },apiKey, language)
//
//        subscriptions.add(subscription)
//    }
}