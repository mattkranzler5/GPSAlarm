package com.squeezymo.gpsalarm.ui.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.squeezymo.gpsalarm.helper.fab
import com.squeezymo.gpsalarm.helper.recyclerView
import com.squeezymo.gpsalarm.ui.fragment.adapter.AlarmsListAdapter
import org.jetbrains.anko.*

class AlarmsListFragment : BaseFragment() {

    lateinit var list: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return UI {
            frameLayout() {
                list = recyclerView()

                fab() {
                    onClick {/* activity.createNewAlarm()*/ }
                }.lparams(
                        height = wrapContent,
                        width = wrapContent,
                        gravity = Gravity.BOTTOM or Gravity.END
                )
            }
        }.view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = AlarmsListAdapter()
    }

}