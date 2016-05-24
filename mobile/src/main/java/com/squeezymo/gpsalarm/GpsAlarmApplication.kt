package com.squeezymo.gpsalarm

import com.orm.SugarApp
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.squeezymo.gpsalarm.injection.AppContextComponent
import com.squeezymo.gpsalarm.injection.AppContextModule
import com.squeezymo.gpsalarm.injection.DaggerAppContextComponent
import javax.inject.Inject

class GpsAlarmApplication : SugarApp() {

    lateinit var graph: AppContextComponent

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppContextComponent.builder().appContextModule(AppContextModule(this)).build()
    }

}



