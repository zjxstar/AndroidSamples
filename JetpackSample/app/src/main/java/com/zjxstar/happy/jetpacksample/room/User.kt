package com.zjxstar.happy.jetpacksample.room

import android.graphics.Bitmap
import androidx.room.*

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */

//@Fts4
@Entity(indices = arrayOf(Index(value = ["last_name"])))
data class User (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val age: Int,
    @Embedded val address: Address?
)

data class Address(
    val street: String?,
    val state: String?,
    val city: String?,
    val region: String?,
    @ColumnInfo(name = "post_code") val postCode: Int
)