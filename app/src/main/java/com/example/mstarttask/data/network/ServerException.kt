package com.example.mstarttask.data.network

import java.io.IOException

class ServerException(val code:Int, override val message: String?) : IOException()