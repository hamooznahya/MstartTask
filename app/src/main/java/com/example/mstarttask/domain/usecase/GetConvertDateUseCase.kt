package com.example.mstarttask.domain.usecase

import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.domain.repository.DateRepositories
import com.example.mstarttask.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConvertDateUseCase @Inject constructor(
    private val repository: DateRepositories
) {
    fun execute(date: String): Flow<ResponseState<DateResponse>> {
        return repository.convertDate(date)
    }
}