package com.squeezymo.gpsalarm.ui.fragment

import android.location.LocationManager
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import com.squareup.leakcanary.RefWatcher
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.injection.AppContextComponent
import com.squeezymo.gpsalarm.ui.activity.MainActivity

import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    protected lateinit var appContextComponent: AppContextComponent

    @Inject protected lateinit var baseActivity: MainActivity
    @Inject protected lateinit var refWatcher: RefWatcher
    @Inject protected lateinit var locationManager: LocationManager

    private var restoredFromBackstack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).graph.inject(this)
        appContextComponent = (baseActivity.application as GpsAlarmApplication).graph

        restoredFromBackstack = false
    }

    override fun onStart() {
        super.onStart()
        if (restoredFromBackstack) onRestoreFromBackstack()
    }

    @CallSuper
    open fun onRestoreFromBackstack() { }

    override fun onDestroyView() {
        super.onDestroyView()
        restoredFromBackstack = true
    }

    override fun onDestroy() {
        super.onDestroy()
        refWatcher.watch(this)
    }

}