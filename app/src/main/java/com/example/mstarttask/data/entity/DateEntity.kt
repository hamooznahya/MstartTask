package com.example.mstarttask.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
data class DateEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @ColumnInfo val eventName: String,
    @ColumnInfo val eventDescription: String,
    @ColumnInfo val GregorianDate: String,
    @ColumnInfo val HijriDate: String,
    @ColumnInfo val serverDatetime: String
)
