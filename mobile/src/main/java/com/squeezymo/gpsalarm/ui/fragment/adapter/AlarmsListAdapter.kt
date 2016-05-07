package com.squeezymo.gpsalarm.ui.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class AlarmsListAdapter : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(position)
    }

    override fun getItemCount(): Int {
        throw UnsupportedOperationException()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        throw UnsupportedOperationException()
    }

}

class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int) {

    }

}