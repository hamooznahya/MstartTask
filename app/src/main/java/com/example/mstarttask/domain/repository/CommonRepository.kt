package com.example.mstarttask.domain.repository


import com.example.mstarttask.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow
interface CommonRepository {

   fun changeLanguage(newLanguage: String):Flow<AppSettings>
    fun getAppSettings(): Flow<AppSettings>

}