package com.zjxstar.happy.bluetoothsample

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.os.Bundle
import android.os.Handler
import android.os.Message
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.util.*

/**
 * 蓝牙服务名
 */
const val SERVICE_NAME = "BluetoothServer"

/**
 * 蓝牙连接状态
 */
const val STATE_NONE = 0       // we're doing nothing
const val STATE_LISTEN = 1     // now listening for incoming connections
const val STATE_CONNECTING = 2 // now initiating an outgoing connection
const val STATE_CONNECTED = 3  // now connected to a remote device

/**
 * 消息状态码
 */
const val MESSAGE_STATE_CHANGE = 1
const val MESSAGE_READ = 2
const val MESSAGE_WRITE = 3
const val MESSAGE_DEVICE_NAME = 4
const val MESSAGE_TOAST = 5

const val DEVICE_NAME = "device_name"
const val TOAST = "toast"

/**
 * 蓝牙连接UUID标识
 */
val SERVICE_UUID: UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66")

/**
 * 蓝牙基本服务
 * @author zjxstar
 * @date Created on 2019-07-05
 */
class BluetoothService(val handler: Handler) {

    /**
     * 蓝牙适配器
     */
    private val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    /**
     * 服务端等待线程
     */
    private var mAcceptThread: AcceptThread? = null

    /**
     * 客户端连接线程
     */
    private var mConnectThread: ConnectThread? = null

    /**
     * 已连接数据通信线程
     */
    private var mConnectedThread: ConnectedThread? = null

    /**
     * 蓝牙当前连接状态
     */
    var mState = STATE_NONE

    /**
     * 开启服务
     */
    @Synchronized fun start() {
        if (mConnectThread != null) {
            mConnectThread?.cancel()
            mConnectThread = null
        }

        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        if (mAcceptThread == null) {
            mAcceptThread = AcceptThread()
            mAcceptThread?.start()
        }
    }

    /**
     * 连接一个蓝牙设备
     */
    @Synchronized fun connect(device: BluetoothDevice) {
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread?.cancel()
                mConnectThread = null
            }
        }

        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        mConnectThread = ConnectThread(device)
        mConnectThread?.start()
    }

    /**
     * 开始蓝牙连接线程，管理连接
     */
    @Synchronized fun connected(socket: BluetoothSocket?, device: BluetoothDevice) {
        if (mConnectThread != null) {
            mConnectThread?.cancel()
            mConnectThread = null
        }

        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        if (mAcceptThread != null) {
            mAcceptThread?.cancel()
            mAcceptThread = null
        }

        mConnectedThread = ConnectedThread(socket)
        mConnectedThread?.start()

        val msg = handler.obtainMessage(MESSAGE_DEVICE_NAME)
        val bundle = Bundle()
        bundle.putString(DEVICE_NAME, device.name)
        msg.data = bundle
        handler.sendMessage(msg)
    }

    /**
     * 停止所有线程
     */
    @Synchronized fun stop() {
        if (mConnectThread != null) {
            mConnectThread?.cancel()
            mConnectThread = null
        }

        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        if (mAcceptThread != null) {
            mAcceptThread?.cancel()
            mAcceptThread = null
        }

        mState = STATE_NONE
    }

    /**
     * 写数据
     */
    fun write(out: ByteArray) {
        var temp: ConnectedThread?
        synchronized(this) {
            if (mState != STATE_CONNECTED) {
                return
            }
            temp = mConnectedThread
        }
        temp?.write(out)
    }

    /**
     * 客户端连接失败
     */
    private fun connectionFailed() {
        val msg = handler.obtainMessage(MESSAGE_TOAST)
        val bundle = Bundle()
        bundle.putString(TOAST, "Unable to connect device")
        msg.data = bundle
        handler.sendMessage(msg)

        mState = STATE_NONE

        // 重新开始服务
        this@BluetoothService.start()
    }

    /**
     * 读数据时连接丢失
     */
    private fun connectionLost() {
        val msg = handler.obtainMessage(MESSAGE_TOAST)
        val bundle = Bundle()
        bundle.putString(TOAST, "Device connection was lost")
        msg.data = bundle
        handler.sendMessage(msg)

        mState = STATE_NONE

        // 重新开始服务
        this@BluetoothService.start()
    }

    /**
     * 蓝牙服务端线程，等待客户端连接
     */
    inner class AcceptThread : Thread() {

        private var serverSocket: BluetoothServerSocket? = null

        init {
            try {
                serverSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(SERVICE_NAME, SERVICE_UUID)
            } catch (e: Exception) {

            }
            mState = STATE_LISTEN
        }

        override fun run() {
            var socket: BluetoothSocket? = null
            while (mState != STATE_CONNECTED) {
                try {
                    socket = serverSocket?.accept()
                } catch (e: Exception) {
                    break
                }
                if (socket != null) {
                    synchronized(this@BluetoothService) {
                        when(mState) {
                            STATE_LISTEN, STATE_CONNECTING -> connected(socket, socket.remoteDevice)
                            STATE_NONE, STATE_CONNECTED -> {
                                try {
                                    socket.close()
                                } catch (e: Exception){}
                            }
                        }
                    }
                }
            }
        }

        fun cancel() {
            try {
                serverSocket?.close()
            } catch (e: Exception) {

            }
        }

    }

    /**
     * 蓝牙客户端线程，选择服务端进行连接
     */
    inner class ConnectThread(val device: BluetoothDevice) : Thread() {
        private var socket: BluetoothSocket? = null

        init {
            try {
                socket = device.createRfcommSocketToServiceRecord(SERVICE_UUID)
            } catch (e: Exception) {

            }
            mState = STATE_CONNECTING
        }

        override fun run() {
            mBluetoothAdapter.cancelDiscovery()

            try {
                socket?.connect()
            } catch (e: Exception) {
                try {
                    socket?.close()
                } catch (e: Exception) {

                }
                connectionFailed()
                return
            }

            synchronized(this@BluetoothService) {
                mConnectThread = null
            }

            connected(socket, device)
        }

        fun cancel() {
            try {
                socket?.close()
            } catch (e: Exception) {

            }
        }
    }

    /**
     * 已完成连接，可以在此线程进行数据通信
     */
    inner class ConnectedThread(val socket: BluetoothSocket?) : Thread() {
        private var mInStream: InputStream? = null
        private var mOutStream: OutputStream? = null

        init {
            try {
                mInStream = socket!!.inputStream
                mOutStream = socket.outputStream
            } catch (e: Exception) {

            }
            mState = STATE_CONNECTED
        }

        override fun run() {
            val buffer = ByteArray(1024)
            var bytes: Int
            while (mState == STATE_CONNECTED) {
                try {
                    bytes = mInStream!!.read(buffer)

                    handler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget()
                } catch (e: Exception) {
                    connectionLost()
                    break
                }
            }
        }

        fun write(buffer: ByteArray) {
            try {
                mOutStream!!.write(buffer)

                handler.obtainMessage(MESSAGE_WRITE, -1, -1, buffer).sendToTarget()
            } catch (e: Exception) {

            }
        }

        fun cancel() {
            try {
                socket!!.close()
            } catch (e: Exception) {

            }
        }
    }

}
