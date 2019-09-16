package com.zjxstar.happy.jetpacksample.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */

@Database(entities = arrayOf(User::class),
    views = arrayOf(UserDetail::class),
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}