package com.zjxstar.happy.jetpacksample.room

import androidx.room.ColumnInfo

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */
data class NameTuple(
    @ColumnInfo(name = "first_name") val firstName: String?,

    @ColumnInfo(name = "last_name") val lastName: String?
)