package com.zjxstar.happy.jetpacksample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.zjxstar.happy.jetpacksample.databinding.*
import com.zjxstar.happy.jetpacksample.lifecycle.NameActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 主界面
 *
 * @author zjxstar
 * @date Created on 2019-07-03
 */
class MainActivity : AppCompatActivity() {

    lateinit var mGoDataBindingBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGoDataBindingBtn = findViewById(R.id.go_databinding_btn)
        mGoDataBindingBtn.apply {
            setOnClickListener { view ->
                val intent = Intent(context, TwoWayBindingActivity::class.java)
                startActivity(intent)
            }
        }

        go_lifecycle_btn.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
        }
    }
}
