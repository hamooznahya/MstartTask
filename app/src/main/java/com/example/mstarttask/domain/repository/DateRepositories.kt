package com.example.mstarttask.domain.repository

import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.data.entity.DateEntity
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface DateRepositories {
    fun convertDate(date: String): Flow<ResponseState<DateResponse>>
    fun getAllEvent():  Flow<List<DateModel>>
    fun saveEvent(item: DateModel)
    fun deleteEvent(date: DateModel)
    fun updateEvent(date: DateModel)
    fun deleteById(date: List<Int>)
}