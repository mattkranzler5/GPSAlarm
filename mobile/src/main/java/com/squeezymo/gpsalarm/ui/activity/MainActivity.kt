package com.squeezymo.gpsalarm.ui.activity

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.helper.showFragment
import com.squeezymo.gpsalarm.injection.ActivityContextComponent
import com.squeezymo.gpsalarm.injection.ActivityContextModule
import com.squeezymo.gpsalarm.ui.fragment.AlarmsListFragment
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic lateinit var graph: ActivityContextComponent
    }

    lateinit var primaryFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        graph = GpsAlarmApplication.graph.newActivityContextComponent(ActivityContextModule(this))

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showAlarmsList()
        }
        else {
            primaryFragment = fragmentManager.getFragment(savedInstanceState, "primaryFragment");
            showFragment(R.id.primary_container, primaryFragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentManager.putFragment(outState, "primaryFragment", primaryFragment);
    }

    fun showAlarmsList() {
        primaryFragment = AlarmsListFragment()
        showFragment(R.id.primary_container, primaryFragment)
    }

    fun createNewAlarm() {
        toast("new alarm")
    }

}
