package com.example.mstarttask.domain.usecase

import com.example.mstarttask.domain.model.AppSettings
import com.example.mstarttask.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppLanguageUseCase @Inject constructor
    (private var repository: CommonRepository) {
    fun execute(): Flow<AppSettings> {
        return repository.getAppSettings()
    }


}