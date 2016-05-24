package com.squeezymo.gpsalarm.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import butterknife.bindView
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.helper.findFragmentById
import com.squeezymo.gpsalarm.helper.showFragment
import com.squeezymo.gpsalarm.injection.ActivityContextComponent
import com.squeezymo.gpsalarm.injection.ActivityContextModule
import com.squeezymo.gpsalarm.ui.fragment.AlarmFragment
import com.squeezymo.gpsalarm.ui.fragment.AlarmsListFragment

class MainActivity : AppCompatActivity() {

    lateinit var graph: ActivityContextComponent
    private val toolbar: Toolbar by bindView<Toolbar>(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        graph = (application as GpsAlarmApplication).graph.newActivityContextComponent(ActivityContextModule(this))

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            showAlarmsList()
        else
            showFragment(R.id.primary_container, supportFragmentManager.getFragment(savedInstanceState, "primaryFragment"))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (findFragmentById<Fragment>(R.id.primary_container) != null)
            supportFragmentManager.putFragment(outState, "primaryFragment", findFragmentById(R.id.primary_container));
    }

    fun showAlarmsList() {
        showFragment(R.id.primary_container, AlarmsListFragment())
    }

    fun createNewAlarm() {
        showFragment(R.id.primary_container, AlarmFragment())
    }

}
