package com.squeezymo.gpsalarm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.ui.PlacePicker
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.helper.findFragmentById
import com.squeezymo.gpsalarm.helper.showFragment
import com.squeezymo.gpsalarm.injection.ActivityContextComponent
import com.squeezymo.gpsalarm.injection.ActivityContextModule
import com.squeezymo.gpsalarm.model.Alarm
import com.squeezymo.gpsalarm.ui.fragment.AlarmFragment
import com.squeezymo.gpsalarm.ui.fragment.AlarmsListFragment
import com.squeezymo.gpsalarm.ui.fragment.BaseFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private val PLACE_PICKER_REQUEST = 42

    lateinit var graph: ActivityContextComponent
    val toolbar: Toolbar by bindView<Toolbar>(R.id.toolbar)

    @Inject lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        graph = (application as GpsAlarmApplication).graph.newActivityContextComponent(ActivityContextModule(this))
        graph.inject(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null)
            showAlarmsList()
        else
            showFragment(R.id.primary_container, supportFragmentManager.getFragment(savedInstanceState, "primaryFragment"))
    }

    override fun onStart() {
        super.onStart()
        googleApiClient.connect()
    }

    override fun onStop() {
        if (googleApiClient.isConnected)
            googleApiClient.disconnect()

        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val mainFragment = findMainFragment()

        if (mainFragment != null)
            supportFragmentManager.putFragment(outState, "primaryFragment", mainFragment);
    }

    fun findMainFragment() = findFragmentById<BaseFragment>(R.id.primary_container)

    fun showAlarmsList() {
        showFragment(R.id.primary_container, AlarmsListFragment())
    }

    fun createNewAlarm() {
        showFragment(R.id.primary_container, AlarmFragment.new())
    }

    fun editAlarm(alarm: Alarm) {
        showFragment(R.id.primary_container, AlarmFragment.forAlarm(alarm))
    }

    fun showPlacePicker() {
        val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        }
        catch (e: GooglePlayServicesNotAvailableException) {
            // TODO
        }
        catch (e: GooglePlayServicesRepairableException) {
            // TODO
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            val place = PlacePicker.getPlace(this, data);
            val fragment = findMainFragment()

            if (fragment is AlarmFragment)
                fragment.onPlacePicked(place)
        }
    }

    override fun onConnected(p0: Bundle?) { }

    override fun onConnectionFailed(p0: ConnectionResult) { }

    override fun onConnectionSuspended(p0: Int) { }

    fun isGooglePlayServicesAvailable(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        return apiAvailability.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
    }

}
