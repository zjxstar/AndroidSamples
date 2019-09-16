package com.zjxstar.happy.jetpacksample.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *
 * @author zjxstar
 * @date Created on 2019-09-11
 */

@Entity
data class Playlist(
    @PrimaryKey var id: Int,
    val name: String?,
    val description: String?
)

@Entity
data class Song(
    @PrimaryKey var id: Int,
    val songName: String?,
    val artistName: String?
)

@Entity(
    tableName = "playlist_song_join",
    primaryKeys = arrayOf("playlistId", "songId"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Playlist::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("playlistId")
        ),
        ForeignKey(
            entity = Song::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("songId")
        )
    )
)
data class PlaylistSongJoin(
    val playlistId: Int,
    val songId: Int
)