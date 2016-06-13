package com.squeezymo.gpsalarm.ui.fragment.adapter

import android.support.v7.widget.RecyclerView
import com.squeezymo.gpsalarm.ui.activity.MainActivity

abstract class BaseAdapter<VH: RecyclerView.ViewHolder>(protected val activity: MainActivity) : RecyclerView.Adapter<VH>() {
}