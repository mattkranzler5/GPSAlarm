package com.squeezymo.gpsalarm.injection

import android.content.Context
import android.location.LocationManager
import com.squareup.leakcanary.RefWatcher
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.model.Alarm
import com.squeezymo.gpsalarm.ui.fragment.BaseFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@ApplicationScope @Component(modules = arrayOf(AppContextModule::class))
interface AppContextComponent {
    fun inject(fragment: BaseFragment)

    fun inject(alarm: Alarm)

    fun newActivityContextComponent(module: ActivityContextModule): ActivityContextComponent
}

@Module
class AppContextModule(val app: GpsAlarmApplication) {
/*
    @Provides @ForApplication
    fun provideContext(): Context {
        return app;
    }
*/

    @Provides
    fun provideGpsAlarmApplicationContext(): GpsAlarmApplication {
        return app;
    }

/*    @Provides
    fun provideRefWatcher(): RefWatcher {
        return app.refWatcher
    }

    @Provides
    fun provideLocationManager(): LocationManager {
        return app.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }*/
}