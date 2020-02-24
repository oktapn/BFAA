package com.okta.moviecatalogue.deps


import com.okta.moviecatalogue.networking.NetworkModule
import com.okta.moviecatalogue.ui.main.MainActivity
import com.okta.moviecatalogue.ui.movie.MovieFragment
import com.okta.moviecatalogue.ui.tvshow.TvShowFragment
import dagger.Component
import javax.inject.Singleton


/**
 * Created by okta on 11/13/2018.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface Deps {

    fun inject(mainActivity: MainActivity)

    fun inject(movieFragment: MovieFragment)

    fun inject(tvShowFragment: TvShowFragment)

}
