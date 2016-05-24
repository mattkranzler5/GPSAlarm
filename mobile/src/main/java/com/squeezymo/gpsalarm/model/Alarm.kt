package com.squeezymo.gpsalarm.model

import android.content.Context
import com.orm.SugarRecord
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.injection.ForApplication
import java.io.Serializable
import javax.inject.Inject

class Alarm @Inject constructor(@ForApplication var context: Context): SugarRecord(), Serializable {

    lateinit var title: String

    init {
        title = context.getString(R.string.default_alarm_title)
    }

}
