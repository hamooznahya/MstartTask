package com.example.mstarttask.data

import com.example.mstarttask.data.dto.BaseResponse
import com.example.mstarttask.data.dto.DateResponse
import com.google.gson.JsonObject

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface APIEndpoints {
    @GET("v1/gToH")
    suspend fun convertDate(
        @Query(value = "date", encoded = true)  countries : String
    ): BaseResponse<DateResponse>
}