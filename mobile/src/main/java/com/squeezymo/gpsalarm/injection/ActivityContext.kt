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

    //fun inject(adapter: BaseAdapter<out RecyclerView.ViewHolder>)
}

@Module
class ActivityContextModule(val activity: MainActivity) {
    /* @Provides @ForActivity
    fun provideContext(): Context {
        return activity;
    }*/

    @Provides
    fun provideMainActivityContext(): MainActivity {
        return activity;
    }
}
