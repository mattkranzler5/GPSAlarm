package com.squeezymo.gpsalarm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.model.Alarm
import org.jetbrains.anko.editText
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.verticalLayout

class AlarmFragment : BaseFragment() {

    private lateinit var alarm: Alarm

    companion object {
        fun new(): AlarmFragment {
            return AlarmFragment()
        }

        fun forAlarm(alarm: Alarm): AlarmFragment {
            val args = Bundle()
            args.putSerializable("alarm", alarm)

            val fragment = AlarmFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        alarm = if (arguments?.getSerializable("alarm") == null) appContextComponent.createAlarm()
                else arguments.getSerializable("alarm") as Alarm

        return UI {
            verticalLayout {
                val title = editText {
                    hint = getString(R.string.hint_alarm_title)
                    //text = "" as CharSequence
                }
            }
        }.view
    }

}