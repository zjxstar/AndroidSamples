package com.zjxstar.happy.jetpacksample.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: User)

    @Insert
    fun insertBothUsers(user1: User, user2: User)

    @Insert
    fun insertUserAndFriends(user: User, friends: List<User>)

    @Update
    fun updateUsers(vararg users: User)

    @Delete
    fun deleteUsers(vararg users: User)

    @Query("SELECT * FROM user")
    fun loadAllUsers(): Array<User>

    @Query("SELECT * FROM user WHERE age > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): Array<User>

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :search " +
    "OR last_name LIKE :search")
    fun findUserWithName(search: String): List<User>

    @Query("SELECT first_name, last_name FROM user")
    fun loadFullName(): List<NameTuple>

    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    fun loadUsersFromRegionsSync(regions: List<String>): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE uid = :id LIMIT 1")
    fun loadUserById(id: Int): Flowable<User>

    @Insert
    fun insertLargeNumberOfUsers(users: List<User>): Maybe<Int>

    @Insert
    fun insertLargeNumberOfUsers(vararg users: User): Completable

    @Delete
    fun deleteAllUsers(users: List<User>): Single<Int>

    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    fun loadRawUsersOlderThan(minAge: Int): Cursor
}