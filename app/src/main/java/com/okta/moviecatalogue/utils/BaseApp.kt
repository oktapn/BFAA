package com.okta.moviecatalogue.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okta.moviecatalogue.deps.DaggerDeps
import com.okta.moviecatalogue.deps.Deps
import com.okta.moviecatalogue.networking.NetworkModule

import java.io.File

/**
 * Modified by okta on 9/03/18.
 */
open class BaseApp : AppCompatActivity() {
    lateinit var deps: Deps
        internal set

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cacheFile = File(cacheDir, "responses")
        deps = DaggerDeps.builder().networkModule(NetworkModule(cacheFile)).build()

    }
}
