package com.asemlab.samples.websocket.remote

import kotlinx.coroutines.flow.MutableSharedFlow

interface SocketCallbacks {

    fun init()

    fun onOpen()

    fun onClose()

    suspend fun onMessage(message: String)

    fun sendMessage(message: String)

    val prices: MutableSharedFlow<Double>
}