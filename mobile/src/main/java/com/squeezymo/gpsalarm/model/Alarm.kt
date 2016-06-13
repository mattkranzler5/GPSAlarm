package com.squeezymo.gpsalarm.model

import android.content.Context
import com.orm.SugarRecord
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.injection.ForApplication
import java.io.Serializable
import javax.inject.Inject

class Alarm @Inject constructor(@ForApplication var context: Context?): SugarRecord(), Serializable {

    var active: Boolean = false
    var title: String?
    var reminder: String?
    var place: PlaceWrapper? = null

    constructor() : this(null)

    init {
        title = context?.getString(R.string.default_alarm_title) ?: ""
        reminder = context?.getString(R.string.default_alarm_reminder) ?: ""
    }

}
