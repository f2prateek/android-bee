package com.f2prateek.bee

import android.app.Application
import timber.log.Timber

class BeeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
