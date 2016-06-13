package com.squeezymo.gpsalarm.injection

import android.content.Context
import android.location.LocationManager
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.model.Alarm
import dagger.Component
import dagger.Module
import dagger.Provides

@ApplicationScope @Component(modules = arrayOf(AppContextModule::class))
interface AppContextComponent {
    fun createAlarm(): Alarm

    fun newActivityContextComponent(module: ActivityContextModule): ActivityContextComponent
}

@Module
class AppContextModule(val app: GpsAlarmApplication) {
    @Provides @ApplicationScope @ForApplication
    fun provideApplicationContext(): Context {
        return app;
    }

    @Provides @ApplicationScope
    fun provideGpsAlarmApplicationContext(): GpsAlarmApplication {
        return app;
    }

    @Provides @ApplicationScope
    fun provideRefWatcher(): RefWatcher {
        return LeakCanary.install(app)
    }

    @Provides @ApplicationScope
    fun provideLocationManager(): LocationManager {
        return app.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}