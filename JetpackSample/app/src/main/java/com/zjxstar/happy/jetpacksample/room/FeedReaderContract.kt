package com.zjxstar.happy.jetpacksample.room

import android.provider.BaseColumns

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-03
 */
class FeedReaderContract {

    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_SUBTITLE = "subtitle"
    }
}