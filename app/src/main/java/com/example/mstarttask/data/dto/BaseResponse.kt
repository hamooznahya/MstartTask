package com.example.mstarttask.data.dto

import com.google.gson.annotations.SerializedName

open class BaseResponse<T> {
    @SerializedName("code")
    var responseCode: Int = -1
    @SerializedName("status")
    var responseMessage: String? = null
    @SerializedName("data")
    var data: T? = null
}