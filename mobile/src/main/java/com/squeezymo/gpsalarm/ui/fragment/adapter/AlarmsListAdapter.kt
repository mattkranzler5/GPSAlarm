package com.squeezymo.gpsalarm.ui.fragment.adapter

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.bindView
import com.orm.SugarRecord
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.model.Alarm
import com.squeezymo.gpsalarm.ui.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.switchCompat

class AlarmsListAdapter(activity: MainActivity) : BaseAdapter<AlarmsListAdapter.ViewHolder>(activity) {
    private val ankoContext = AnkoContext.createReusable(activity, this)
    private val alarms = SugarRecord.listAll(Alarm::class.java)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) { holder?.bind() }

    override fun getItemCount() = alarms.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ViewHolder()

    inner class ViewHolder : RecyclerView.ViewHolder(ViewComposer.createView(ankoContext)) {
        private val title: TextView by bindView<TextView>(ViewComposer.TITLE_ID)
        private val switch: SwitchCompat by bindView<SwitchCompat>(ViewComposer.SWITCH_ID)

        init {
            itemView.onClick { activity.editAlarm(alarms[adapterPosition]) }
            switch.onCheckedChange { v, checked ->
                val alarm = alarms[adapterPosition]

                alarm.active = checked
                alarm.save()
            }
        }

        fun bind() {
            val alarm = alarms[adapterPosition]

            title.text = alarm.title
            switch.isChecked = alarm.active
        }
    }

    private object ViewComposer {
        @IdRes val TITLE_ID = View.generateViewId();
        @IdRes val SWITCH_ID = View.generateViewId();

        fun createView(ui: AnkoContext<AlarmsListAdapter>): View {
            val view = ui.apply {
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        id = TITLE_ID
                        text = ui.ctx.getString(R.string.default_alarm_title)
                    }.lparams(width = 0, weight = 1f)

                    switchCompat {
                        id = SWITCH_ID
                    }
                }
            }.view

            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            return view
        }
    }
}