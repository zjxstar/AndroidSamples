package com.zjxstar.happy.jetpacksample.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zjxstar.happy.jetpacksample.R

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " + "PRIMARY KEY(`id`))")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
    }
}


class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }


    private fun createDB() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-db"
        ).build()
    }

    private fun migration() {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
    }
}