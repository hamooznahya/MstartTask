package com.example.mstarttask.utils

import com.example.mstarttask.data.network.ServerException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException


fun Throwable.mapToError(): ResponseError {
    return when (this) {
        is HttpException -> {
            (ResponseError.createServerError(this.code().toString(), this.message()))
        }
        is ServerException -> {
            (ResponseError.createServerError(this.code.toString(), this.message.orEmpty()))
        }
        is SocketTimeoutException -> {
            (ResponseError.ERROR_TIME_OUT)
        }
        is SSLHandshakeException -> {
            ResponseError.createGenericError("123", "SSLHandshakeException")
        }
        is MalformedJsonException, is IllegalArgumentException, is IllegalStateException, is JsonSyntaxException -> {
            (ResponseError.createGenericError("123", "ERROR_CANNOT_PARSE_JSON"))
        }
        is IOException -> {
            (ResponseError.ERROR_NO_INTERNET_CONNECTION)
        }
        is Exception -> {
            (ResponseError.createGenericError("123", this.message.orEmpty()))
        }

        else -> ResponseError.ERROR_INTERNAL_ERROR
    }
}




