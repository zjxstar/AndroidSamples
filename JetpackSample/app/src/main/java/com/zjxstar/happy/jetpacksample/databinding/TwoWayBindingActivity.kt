package com.zjxstar.happy.jetpacksample.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zjxstar.happy.jetpacksample.R

class TwoWayBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTwoWayBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding)
        binding.orderTypeCode = "0002"
    }
}
