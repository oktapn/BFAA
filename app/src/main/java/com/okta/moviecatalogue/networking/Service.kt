package com.okta.moviecatalogue.networking

import android.util.Log
import com.okta.moviecatalogue.model.movielist.ResponseMovie
import com.okta.moviecatalogue.model.tvshowlist.ResponseTvShow
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Service(private val networkService: NetworkService) {


    fun getMovie(getMovieCallback: GetMovieCallback, apiKey: String, language: String): Subscription {
        return networkService.getMovie(apiKey, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<ResponseMovie>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getMovieCallback.onError(NetworkError(e))
                }

                override fun onNext(responseProduct: ResponseMovie) {
                    getMovieCallback.onSuccess(responseProduct)
                }
            })
    }

    interface GetMovieCallback {
        fun onSuccess(response: ResponseMovie)

        fun onError(networkError: NetworkError)
    }

    fun getTvShow(getTvshowCallback: GetTvshowCallback, apiKey: String, language: String): Subscription {
        return networkService.getTvShow(apiKey, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable -> Observable.error(throwable) }
            .subscribe(object : Subscriber<ResponseTvShow>() {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    getTvshowCallback.onError(NetworkError(e))
                }

                override fun onNext(responseTvshow: ResponseTvShow) {
                    getTvshowCallback.onSuccess(responseTvshow)
                }
            })
    }

    interface GetTvshowCallback {
        fun onSuccess(response: ResponseTvShow)

        fun onError(networkError: NetworkError)
    }

}
