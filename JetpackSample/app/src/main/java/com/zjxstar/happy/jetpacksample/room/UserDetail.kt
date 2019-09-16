package com.zjxstar.happy.jetpacksample.room

import androidx.room.DatabaseView

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */
@DatabaseView("SELECT user.id, user.departmentId," +
"department.name AS departmentName FROM user " +
"INNER JOIN department ON user.departmentId=department.id")
data class UserDetail(
    val id: Long,
    val name: String?,
    val departmentId: Long,
    val departmentName: String?
)