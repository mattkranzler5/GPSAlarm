package com.squeezymo.gpsalarm

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class GpsAlarmApplication : Application() {

    companion object {
        @JvmStatic lateinit var refWatcher: RefWatcher
    }

    override fun onCreate() {
        super.onCreate()
        refWatcher = LeakCanary.install(this)
    }

}