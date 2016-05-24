package com.squeezymo.gpsalarm.helper

import android.app.Activity
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View

inline fun <reified T : View> View.find(id: Int): T = findViewById(id) as T
inline fun <reified T : View> Activity.find(id: Int): T = findViewById(id) as T
inline fun <reified T : View> Fragment.find(id: Int): T = view?.findViewById(id) as T

inline fun <reified T : View> View.findOptional(id: Int): T? = findViewById(id) as? T
inline fun <reified T : View> Activity.findOptional(id: Int): T? = findViewById(id) as? T
inline fun <reified T : View> Fragment.findOptional(id: Int): T? = view?.findViewById(id) as? T

inline fun <reified T : Fragment> AppCompatActivity.findFragmentById(id: Int): T? {
    return supportFragmentManager.findFragmentById(id) as? T
}

fun AppCompatActivity.showFragment(@LayoutRes containerViewId: Int, fragment: Fragment): Fragment {
    supportFragmentManager.beginTransaction().replace(containerViewId, fragment).addToBackStack(null).commit()
    return fragment
}