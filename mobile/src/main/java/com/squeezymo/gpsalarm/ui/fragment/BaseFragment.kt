package com.squeezymo.gpsalarm.ui.fragment

import android.app.Fragment
import android.location.LocationManager
import android.os.Bundle
import com.squareup.leakcanary.RefWatcher
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.injection.ActivityScope
import com.squeezymo.gpsalarm.injection.ForActivity
import com.squeezymo.gpsalarm.injection.ForApplication
import com.squeezymo.gpsalarm.ui.activity.MainActivity
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject lateinit var activity1: MainActivity
    //@Inject @field:[ForActivity] lateinit var activity1: MainActivity
    //@Inject @field:[ForApplication] lateinit var context1: GpsAlarmApplication

    //@Inject lateinit var refWatcher: RefWatcher
    //@Inject lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //GpsAlarmApplication.graph.inject(this)
        MainActivity.graph.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //refWatcher.watch(this)
    }

}