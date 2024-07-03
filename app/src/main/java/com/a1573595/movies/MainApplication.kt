package com.a1573595.movies

import android.app.Application
import com.a1573595.movies.data.AppContainer
import com.a1573595.movies.data.DefaultAppContainer

class MainApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer()
    }
}