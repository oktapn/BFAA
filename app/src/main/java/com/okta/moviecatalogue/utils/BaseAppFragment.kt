package com.okta.moviecatalogue.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.okta.moviecatalogue.deps.DaggerDeps
import com.okta.moviecatalogue.deps.Deps
import com.okta.moviecatalogue.networking.NetworkModule

import java.io.File

open class BaseAppFragment : Fragment() {
    lateinit var deps: Deps
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cacheFile = File(context?.cacheDir, "responses")
        deps = DaggerDeps.builder().networkModule(NetworkModule(cacheFile)).build()
    }
}
