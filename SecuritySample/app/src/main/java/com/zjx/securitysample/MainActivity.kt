package com.zjx.securitysample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zjx.securitysample.base64.Base64Activity
import com.zjx.securitysample.md5.MD5Activity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toMD5Page(view: View) {
        val intent = Intent(this, MD5Activity::class.java)
        startActivity(intent)
    }

    fun toBase64Page(view: View) {
        val intent = Intent(this, Base64Activity::class.java)
        startActivity(intent)
    }
}
