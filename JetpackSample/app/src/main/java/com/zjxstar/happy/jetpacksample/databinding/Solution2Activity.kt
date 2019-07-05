package com.zjxstar.happy.jetpacksample.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.zjxstar.happy.jetpacksample.R

/**
 *
 * @author zjxstar
 * @date Created on 2019-07-04
 */
class Solution2Activity : AppCompatActivity() {

    private val viewmodel by lazy {
        ViewModelProviders.of(this).get(SimpleViewModelSolution::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySolution2Binding = DataBindingUtil.setContentView(this, R.layout.activity_solution2)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

}