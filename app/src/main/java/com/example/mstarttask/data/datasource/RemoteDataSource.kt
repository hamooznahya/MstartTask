package com.example.mstarttask.data.datasource

import com.example.mstarttask.data.dto.BaseResponse
import com.example.mstarttask.data.dto.DateResponse

interface RemoteDataSource {

    suspend fun convertDate(date: String): Result<BaseResponse<DateResponse>>
}