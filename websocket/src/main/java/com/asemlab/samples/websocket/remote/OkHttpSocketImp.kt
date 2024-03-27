package com.asemlab.samples.websocket.remote

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class OkHttpSocketImp @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val request: Request
) : SocketCallbacks, WebSocketListener() {

    private val TAG = OkHttpSocketImp::class.java.name
    private lateinit var webSocket: WebSocket
    override val prices = MutableSharedFlow<Double>()


    override fun init() {
        webSocket = okHttpClient.newWebSocket(request, this)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        try {
            runBlocking {
                onMessage(text)
            }
        } catch (e: NumberFormatException) {
            Log.e(TAG, "${e.message}")
        }
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d(TAG, "onOpen WebSocket")
        webSocket.send("dollar")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d(TAG, "onClosed WebSocket")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.e(TAG, "onFailure ${t.message} WebSocket")
    }


    override fun onOpen() {

    }

    override fun onClose() {

    }

    override suspend fun onMessage(message: String) {
        prices.emit(message.toDouble())
    }

    override fun sendMessage(message: String) {
        webSocket.send(message)
    }
}