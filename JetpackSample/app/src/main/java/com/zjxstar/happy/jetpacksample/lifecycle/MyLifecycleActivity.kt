package com.zjxstar.happy.jetpacksample.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-02
 */
class MyLifecycleActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myLocationListener = MyLocationListener(this, lifecycle) {
            location -> // update UI
        }


    }
}