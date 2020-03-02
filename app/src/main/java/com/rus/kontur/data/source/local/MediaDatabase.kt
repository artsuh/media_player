package com.rus.kontur.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rus.kontur.data.Audio

@Database(entities = [Audio::class], version = 1, exportSchema = false)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun audioDao(): AudioDao
}