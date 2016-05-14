package com.squeezymo.gpsalarm.helper

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.recyclerView() = recyclerView {}
inline fun ViewManager.recyclerView(init: RecyclerView.() -> Unit) = ankoView({ RecyclerView(it) }, init)

inline fun ViewManager.fab() = fab {}
inline fun ViewManager.fab(init: FloatingActionButton.() -> Unit) = ankoView({ FloatingActionButton(it) }, init)