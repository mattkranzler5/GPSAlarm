package com.squeezymo.gpsalarm.ui.fragment

import android.app.Fragment
import com.squeezymo.gpsalarm.GpsAlarmApplication

abstract class BaseFragment : Fragment() {

    override fun onDestroy() {
        super.onDestroy()
        GpsAlarmApplication.refWatcher.watch(this)
    }

}