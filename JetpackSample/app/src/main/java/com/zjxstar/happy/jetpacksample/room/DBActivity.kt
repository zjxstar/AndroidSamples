package com.zjxstar.happy.jetpacksample.room

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zjxstar.happy.jetpacksample.R
import kotlinx.android.synthetic.main.activity_db.*

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-03
 */

class DBActivity : AppCompatActivity() {

    val dbHelper = FeedReaderDbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        db_write_btn.setOnClickListener {
            writeDB()
        }

        db_read_btn.setOnClickListener {
            readDB()
        }

        db_delete_btn.setOnClickListener {
            deleteDB()
        }

        db_update_btn.setOnClickListener {
            updateDB()
        }
    }

    private fun writeDB() {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "AA")
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "aaaa")
        }

        val newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
        Toast.makeText(this, newRowId.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun readDB() {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE)

        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} = ?"
        val selectionArgs = arrayOf("AA")

        val sortOrder = "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder)

        val sb = StringBuilder()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndex(BaseColumns._ID))
                val subTitle = getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE))
                sb.append(itemId).append(" ").append(subTitle).append("\n")
            }
        }
        db_content_tv.setText(sb.toString())
    }

    private fun deleteDB() {
        val db = dbHelper.writableDatabase
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("AA")
        val deleteRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs)
        Toast.makeText(this, "delete rows : ${deleteRows}", Toast.LENGTH_SHORT).show()
    }

    private fun updateDB() {
        val db = dbHelper.writableDatabase

        val title = "MyNewTitle"
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title)
        }

        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("AA")
        val count = db.update(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
        Toast.makeText(this, "update rows: ${count}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}