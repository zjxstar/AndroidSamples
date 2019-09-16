package com.zjxstar.happy.jetpacksample.room

import androidx.room.TypeConverter
import java.util.*

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-12
 */

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}