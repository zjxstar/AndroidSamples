package com.zjxstar.happy.jetpacksample.databinding

import android.text.TextUtils
import android.util.Log
import androidx.databinding.InverseMethod

/**
 *
 * @author zjxstar
 * @date Created on 2019-07-05
 */

object InverseMethodModel {

    @InverseMethod("orderTypeToString")
    @JvmStatic
    fun stringToOrderType(str: String): String {
        val result = when(str) {
            "A" -> "0001"
            "B" -> "0002"
            else -> ""
        }
        Log.d("InverseMethodModel", result)
        return result
    }

    @JvmStatic
    fun orderTypeToString(type: String): String {
        return when(type) {
            "0001" -> "A"
            "0002" -> "B"
            else -> ""
        }
    }
}