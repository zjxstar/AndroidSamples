package com.zjxstar.happy.jetpacksample.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.zjxstar.happy.jetpacksample.R

class SolutionActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SimpleViewModelSolution::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution)
        val binding: ActivitySolutionBinding = DataBindingUtil.setContentView(this, R.layout.activity_solution)
//        val binding: ActivitySolutionBinding = ActivitySolutionBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }
}
