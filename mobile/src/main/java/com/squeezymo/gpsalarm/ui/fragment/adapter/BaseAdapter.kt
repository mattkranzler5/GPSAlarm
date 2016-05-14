package com.squeezymo.gpsalarm.ui.fragment.adapter

import android.support.v7.widget.RecyclerView
import com.squeezymo.gpsalarm.ui.activity.MainActivity
import javax.inject.Inject

abstract class BaseAdapter<VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    @Inject lateinit var context: MainActivity

    init {
    //    MainActivity.graph.inject(this)
    }

}