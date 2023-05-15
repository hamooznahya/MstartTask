package com.example.mstarttask.data.datasource

import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.data.entity.DateEntity
import com.example.mstarttask.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface OfflineDataSource {
    fun addItem(item: DateEntity)
    fun getItems():  Flow<List<DateEntity>>
    fun updateItem(item: DateEntity)
    fun deleteItem(item: DateEntity)
    fun deleteById(id: List<Int>)

    suspend fun saveAppLanguage(appSettings: AppSettings)
    fun getAppLanguage() : Flow<AppSettings>
}


