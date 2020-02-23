package com.okta.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.okta.moviecatalogue.R
import com.okta.moviecatalogue.model.movielist.ResultListMovie
import com.okta.moviecatalogue.model.tvshowlist.ResultListTvShow
import kotlinx.android.synthetic.main.activity_detail.*
import android.view.MenuItem


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val TYPE = "type"
        const val MOVIE ="movie"
        const val TV = "tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionbar = supportActionBar
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            val type = intent.getStringExtra(TYPE)
            when(type){
                MOVIE -> {
                    //set actionbar title
                    actionbar.title = getString(R.string.detail_movie)
                    val movie = intent.getParcelableExtra(EXTRA_MOVIE) as ResultListMovie
                    txt_description_dtl.text = movie.overview
                    txt_name_dtl.text = movie.title
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w185"+movie.posterPath)
                        .into(img_photo_dtl)
                }
                TV -> {
                    actionbar.title = getString(R.string.detail_tvshow)
                    val tvShow = intent.getParcelableExtra(EXTRA_MOVIE) as ResultListTvShow
                    txt_description_dtl.text = tvShow.overview
                    txt_name_dtl.text = tvShow.name
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w185"+tvShow.posterPath)
                        .into(img_photo_dtl)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
