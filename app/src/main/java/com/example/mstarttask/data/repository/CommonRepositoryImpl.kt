package com.example.mstarttask.data.repository

import com.example.mstarttask.data.datasource.OfflineDataSource
import com.example.mstarttask.domain.model.AppSettings
import com.example.mstarttask.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val offlineDataSource: OfflineDataSource,
) : CommonRepository {


    override fun changeLanguage(newLanguage: String): Flow<AppSettings> {
        return flow {
            val appSettings = offlineDataSource.getAppLanguage().first()
            appSettings.language = newLanguage
            offlineDataSource.saveAppLanguage(appSettings)
            emit(appSettings)
        }
    }

    override fun getAppSettings(): Flow<AppSettings> {
        return offlineDataSource.getAppLanguage()
    }

}
