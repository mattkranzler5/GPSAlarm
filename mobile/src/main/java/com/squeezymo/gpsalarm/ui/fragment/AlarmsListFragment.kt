package com.squeezymo.gpsalarm.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squeezymo.gpsalarm.helper.fab
import com.squeezymo.gpsalarm.helper.recyclerView
import com.squeezymo.gpsalarm.ui.fragment.adapter.AlarmsListAdapter
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.wrapContent

class AlarmsListFragment : BaseFragment() {

    private lateinit var list: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return UI {
            frameLayout() {
                list = recyclerView()

                fab() {
                    onClick { baseActivity.createNewAlarm() }
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

        list.layoutManager = LinearLayoutManager(baseActivity)
        list.adapter = AlarmsListAdapter(baseActivity)
    }

    override fun onRestoreFromBackstack() {
        super.onRestoreFromBackstack()
        list.adapter.notifyDataSetChanged()
    }

}