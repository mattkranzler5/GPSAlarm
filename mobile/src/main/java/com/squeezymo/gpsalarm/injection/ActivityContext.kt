package com.squeezymo.gpsalarm.injection

import android.content.Context
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Places
import com.squeezymo.gpsalarm.ui.activity.MainActivity
import com.squeezymo.gpsalarm.ui.fragment.BaseFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ActivityScope @Subcomponent(modules = arrayOf(ActivityContextModule::class))
interface ActivityContextComponent {
    fun inject(fragment: BaseFragment)

    fun inject(activity: MainActivity)
}

@Module
class ActivityContextModule(val activity: MainActivity) {
    @Provides @ActivityScope @ForActivity
    fun provideActivityContext(): Context {
        return activity;
    }

    @Provides @ActivityScope
    fun provideMainActivityContext(): MainActivity {
        return activity;
    }

    @Provides
    fun provideGoogleApiClient(): GoogleApiClient {
        return GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, 0, activity)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(activity)
                .addOnConnectionFailedListener(activity)
                .build()
    }
}
