package com.example.mstarttask.data.dto

class DateResponse(
     val hijri: Hijri,
     val gregorian: Gregorian
)

data class Hijri(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation,
    val holidays: List<String>
)

data class Gregorian(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation
)

data class Weekday(
    val en: String,
    val ar: String? = null
)

data class Month(
    val number: Int,
    val en: String,
    val ar: String? = null
)

data class Designation(
    val abbreviated: String,
    val expanded: String
)