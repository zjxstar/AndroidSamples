package com.zjxstar.happy.jetpacksample.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-02
 */

class MyLiveData : LiveData<String>() {

    override fun onActive() {
        super.onActive()
    }


    override fun onInactive() {
        super.onInactive()
    }

    override fun setValue(value: String?) {
        super.setValue(value)
    }

    companion object {
        private lateinit var sInstance: MyLiveData

        @MainThread
        fun get(): MyLiveData {
            sInstance = if (::sInstance.isInitialized) sInstance else MyLiveData()
            return sInstance
        }
    }

}