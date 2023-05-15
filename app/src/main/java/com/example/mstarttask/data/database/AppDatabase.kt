package com.example.mstarttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mstarttask.data.entity.DateEntity

@Database(
    entities = [DateEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDetailsDao(): DateDao

}