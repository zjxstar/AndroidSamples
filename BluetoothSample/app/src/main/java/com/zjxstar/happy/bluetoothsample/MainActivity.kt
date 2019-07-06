package com.zjxstar.happy.bluetoothsample

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.TextView
import java.lang.StringBuilder

const val REQUEST_ENABLE_BLUETOOTH = 0xa1

class MainActivity : AppCompatActivity() {

    private val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var mBluetoothService: BluetoothService? = null

    private lateinit var mContentTV: TextView

    private val mContentBuilder = StringBuilder()

    private val mReceiver = MyReceiver()

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContentTV = findViewById(R.id.bluetooth_content_tv)

        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (!mBluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH)
        } else if(mBluetoothService == null) {
            setupService()
        }
    }

    private fun setupService() {
        mBluetoothService = BluetoothService(mHandler)
    }

    override fun onResume() {
        super.onResume()
        if (mBluetoothService!!.mState == STATE_NONE) {
            mBluetoothService?.start()
        }
    }

    fun searchDevices(view: View) {
        val devices = mBluetoothAdapter.bondedDevices
        mContentBuilder.append("已配对设备：\n")
        for(device in devices) {
            mContentBuilder.append(device.name).append(" : ")
            mContentBuilder.append(device.address).append("\n")
        }
        mContentTV.text = mContentBuilder.toString()

        mBluetoothAdapter.startDiscovery()
        mContentBuilder.append("------------------------------：\n")
        mContentBuilder.append("发现设备：\n")
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(mReceiver, intentFilter)
    }

    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (BluetoothDevice.ACTION_FOUND == action) {
                val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                mContentBuilder.append(device.name).append(" : ")
                mContentBuilder.append(device.address).append("\n")
                mContentTV.text = mContentBuilder.toString()
            }
        }

    }

    fun ensureDiscoverable(view: View) {
        if (mBluetoothAdapter.scanMode != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
            startActivity(intent)
        }
        Log.d("MainActivity", "ensureDiscoverable")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_ENABLE_BLUETOOTH -> {
                if (resultCode == RESULT_OK) {
                    setupService()
                } else {
                    Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            }
        }
    }

    override fun onDestroy() {
        mBluetoothService?.stop()
        super.onDestroy()
    }
}
