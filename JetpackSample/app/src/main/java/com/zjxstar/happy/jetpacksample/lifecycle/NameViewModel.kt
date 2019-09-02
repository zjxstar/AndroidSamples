package com.zjxstar.happy.jetpacksample.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-02
 */

class NameViewModel : ViewModel() {
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}