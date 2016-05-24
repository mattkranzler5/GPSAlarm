package com.squeezymo.gpsalarm.injection

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.squeezymo.gpsalarm.ui.activity.MainActivity
import com.squeezymo.gpsalarm.ui.fragment.BaseFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Singleton

@ActivityScope @Subcomponent(modules = arrayOf(ActivityContextModule::class))
interface ActivityContextComponent {
    fun inject(fragment: BaseFragment)
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
}
