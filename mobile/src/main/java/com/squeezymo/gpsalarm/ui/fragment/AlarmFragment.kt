package com.squeezymo.gpsalarm.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import com.google.android.gms.location.places.Place
import com.squeezymo.gpsalarm.R
import com.squeezymo.gpsalarm.model.Alarm
import com.squeezymo.gpsalarm.model.PlaceWrapper
import org.jetbrains.anko.editText
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.textChangedListener
import org.jetbrains.anko.textView
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

        setHasOptionsMenu(true)

        return UI {
            verticalLayout {
                // title
                editText {
                    hint = getString(R.string.hint_alarm_title)
                    setText(alarm.title)
                    textChangedListener {
                        onTextChanged {  text, start, before, count ->
                            val title = if (TextUtils.isEmpty(text.toString())) getString(R.string.default_alarm_title) else text.toString()
                            alarm.title = title
                        }
                    }
                }

                // reminder
                editText {
                    hint = getString(R.string.hint_alarm_reminder)
                    setText(alarm.reminder)
                    textChangedListener {
                        onTextChanged {  text, start, before, count ->
                            val reminder = if (TextUtils.isEmpty(text.toString())) getString(R.string.default_alarm_reminder) else text.toString()
                            alarm.reminder = reminder
                        }
                    }
                }

                // Latitude / Longitude
                textView {
                    text = String.format(
                            "%.03f/%.03f",
                            alarm.place?.latitude,
                            alarm.place?.longitude)
                }
            }
        }.view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.alarm_edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_pick_location -> {
                baseActivity.showPlacePicker()
            }

            R.id.item_confirm -> {
                alarm.save()
                baseActivity.supportFragmentManager.popBackStackImmediate()
            }
        }

        return true
    }

    fun onPlacePicked(place: Place) {
        alarm.place = PlaceWrapper(place)
        alarm.save()
    }

}