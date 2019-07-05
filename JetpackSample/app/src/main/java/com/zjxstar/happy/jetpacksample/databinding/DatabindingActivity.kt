package com.zjxstar.happy.jetpacksample.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableInt
import com.zjxstar.happy.jetpacksample.R

/**
 * DataBinding学习页面
 *
 * @author zjxstar
 * @date Created on 2019-07-03
 */
class DatabindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_databinding)

        val binding: ActivityDatabindingBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_databinding)

        binding.user = User("Tom", "Jerry")
        binding.handlers = MyHandlers()

        binding.task = Task("Task A")
        binding.presenter = Presenter()
        binding.task = Task("Task B")

        val map = ObservableArrayMap<String, Any>().apply {
            put("firstName", "Google")
            put("lastName", "Inc.")
            put("age", 17)
        }

        binding.map = map


    }
}
