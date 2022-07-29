package com.tmdb.moovee

import android.app.Application
import com.moovee.engine.local.DataStoreProvider
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MooveeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DataStoreProvider.init(this)
    }
}
