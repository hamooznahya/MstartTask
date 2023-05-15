package com.example.mstarttask.domain.repository

import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface DateRepository {
    fun convertDate(date: String): Flow<ResponseState<DateResponse>>
    fun getAllEvent():  Flow<List<DateModel>>
    suspend  fun saveEvent(item: DateModel)
    suspend  fun deleteEvent(date: DateModel)
    suspend   fun updateEvent(date: DateModel)
    suspend fun deleteById(date: List<Int>)
}