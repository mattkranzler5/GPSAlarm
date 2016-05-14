package com.squeezymo.gpsalarm.helper

import android.app.Activity
import android.app.Fragment
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View

inline fun <reified T : View> View.find(id: Int): T = findViewById(id) as T
inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id) as T
inline fun <reified T : View> Fragment.find(id: Int): T = view?.findViewById(id) as T

inline fun <reified T : View> View.findOptional(id: Int): T? = findViewById(id) as? T
inline fun <reified T : View> Activity.findOptional(id: Int): T? = findViewById(id) as? T
inline fun <reified T : View> Fragment.findOptional(id: Int): T? = view?.findViewById(id) as? T

inline fun <reified T : Fragment> Activity.findFragmentById(id: Int): T? {
    return fragmentManager.findFragmentById(id) as? T
}

inline fun Activity.showFragment(@LayoutRes containerViewId: Int, fragment: Fragment) {
    fragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
}