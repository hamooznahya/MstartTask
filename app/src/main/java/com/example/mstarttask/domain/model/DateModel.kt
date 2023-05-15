package com.example.mstarttask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateModel(
    var id: Int? = null,
    val eventName: String,
    val eventDescription: String,
    val GregorianDate: String,
    val HijriDate: String,
    val serverDatetime: String,
    var isSelected:Boolean=false
): Parcelable