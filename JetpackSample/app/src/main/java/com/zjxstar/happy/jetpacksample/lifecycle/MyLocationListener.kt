package com.zjxstar.happy.jetpacksample.lifecycle

import android.content.Context
import android.location.Location
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-02
 */
class MyLocationListener(private val context: Context,
                         private val lifecyle: Lifecycle,
                         private val callback: (Location) -> Unit) {
    private var enabled = false;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (enabled) {
            // TODO connect
        }
    }

    fun enable() {
        enabled = true
        if (lifecyle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            // TODO connect if not connected
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // disconnect if connected
    }
}