package com.zjxstar.happy.jetpacksample.databinding

import android.util.Log
import android.view.View

/**
 *
 * @author zjxstar
 * @date Created on 2019-07-03
 */
class Presenter {

    fun onSaveClick(task: Task) {
        Log.d("Presenter", "onSaveClick(task)")
    }

    fun onSaveClick(view: View, task: Task) {
        Log.d("Presenter", "onSaveClick(view, task)")
    }

    fun onCompletedChanged(task: Task, completed: Boolean) {
        Log.d("Presenter", "onCompletedChanged( " + task.name + ", " + completed + " )")
    }

    fun onLongClick(view: View, task: Task): Boolean {
        Log.d("Presenter", "onLongClick" + task.name)
        return false
    }

}