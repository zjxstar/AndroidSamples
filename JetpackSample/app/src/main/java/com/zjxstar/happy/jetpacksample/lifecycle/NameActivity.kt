package com.zjxstar.happy.jetpacksample.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zjxstar.happy.jetpacksample.R
import kotlinx.android.synthetic.main.activity_name.*

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-02
 */

class NameActivity : AppCompatActivity() {

    private lateinit var model: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        model = ViewModelProviders.of(this).get(NameViewModel::class.java)

        val nameObserver = Observer<String> {
            newName -> nameTextView.text = newName
        }

        model.currentName.observe(this, nameObserver)

        updateBtn.setOnClickListener {
            val anotherName = "Tom"
            model.currentName.value = anotherName
        }
    }
}