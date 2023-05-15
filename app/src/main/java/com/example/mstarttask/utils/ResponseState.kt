package com.example.mstarttask.utils



sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Failure(val error: ResponseError) : ResponseState<Nothing>()
    data class Success<T>(val item: T) : ResponseState<T>()

}


