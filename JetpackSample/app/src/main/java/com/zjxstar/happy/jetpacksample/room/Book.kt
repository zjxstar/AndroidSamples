package com.zjxstar.happy.jetpacksample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */
@Entity(
    foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("user_id"))
    )
)
data class Book(
    @PrimaryKey val bookId: Int,
    val title: String?,
    @ColumnInfo(name = "user_id") val userId: Int
)