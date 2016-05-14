package com.squeezymo.gpsalarm.ui.fragment.adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.orm.SugarRecord
import com.squeezymo.gpsalarm.GpsAlarmApplication
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.injection.ForActivity
import com.squeezymo.gpsalarm.model.Alarm
import com.squeezymo.gpsalarm.ui.activity.MainActivity
import javax.inject.Inject

class AlarmsListAdapter : BaseAdapter<ViewHolder>() {

    val alarms = SugarRecord.listAll(Alarm::class.java)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(position)
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(context.layoutInflater.inflate(R.layout.item_alarm, parent, false))
    }

}

class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int) {

    }

}