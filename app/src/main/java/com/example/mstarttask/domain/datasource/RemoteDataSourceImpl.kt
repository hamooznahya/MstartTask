package com.example.mstarttask.domain.datasource

import com.example.mstarttask.data.APIEndpoints
import com.example.mstarttask.data.datasource.RemoteDataSource
import com.example.mstarttask.data.dto.BaseResponse
import com.example.mstarttask.data.dto.DateResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiEndpoints: APIEndpoints
) : RemoteDataSource {

    override suspend fun convertDate(
        date: String
    ): Result<BaseResponse<DateResponse>> {
        return runCatching {
            apiEndpoints.convertDate(date)
        }
    }
}