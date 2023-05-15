package com.example.mstarttask.domain.mapper

import com.example.mstarttask.data.entity.DateEntity
import com.example.mstarttask.domain.model.DateModel

class DateMapper {

    fun mapEntityToDateModel(date: DateModel): DateEntity {
        return DateEntity(
            id = date.id,
            eventName = date.eventName,
            eventDescription = date.eventDescription,
            HijriDate = date.HijriDate,
            GregorianDate = date.GregorianDate,
            serverDatetime = date.serverDatetime
        )
    }
    fun mapDateModelToEntity(date: DateEntity): DateModel {
        return DateModel(
            id = date.id,
            eventName = date.eventName,
            eventDescription = date.eventDescription,
            HijriDate = date.HijriDate,
            GregorianDate = date.GregorianDate,
            serverDatetime = date.serverDatetime
        )
    }
}