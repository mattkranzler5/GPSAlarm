package com.squeezymo.gpsalarm.model

import android.content.Context
import com.orm.SugarRecord
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.injection.ForApplication
import javax.inject.Inject

class Alarm(): SugarRecord() {
    //@Inject @ForApplication lateinit var context: Context

    lateinit var title: String

    init {
        //GpsAlarmApplication.graph.inject(this)
        title = "" //context.getString(R.string.default_alarm_title)
    }

    constructor(title: String) : this() {
        this.title = title
    }
}
