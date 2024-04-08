package com.asemlab.samples.websocket.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.Url
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class KtorSocketImp @Inject constructor(
    private val client: HttpClient,
    private val url: Url
) : SocketCallbacks {

    private val TAG = KtorSocketImp::class.java.simpleName
    private lateinit var session: DefaultWebSocketSession
    override val prices = MutableSharedFlow<Double>()


    override fun init() {
        runBlocking {
            session = client.webSocketSession {
                url(this@KtorSocketImp.url)
            }
            if (session.isActive)
                onOpen()
            else
                onClose()
        }
    }


    override fun onOpen() {
        Log.d(TAG, "onOpen WebSocket")
        CoroutineScope(Dispatchers.IO).launch {
            session.send(Frame.Text("dollar"))
            onMessage("")
        }
    }

    override fun onClose() {
        Log.d(TAG, "onClosed WebSocket")
        runBlocking {
            session.close()
        }
    }

    override suspend fun onMessage(message: String) {
        session
            .incoming
            .receiveAsFlow()
            .filterIsInstance<Frame.Text>()
            .mapNotNull {
                try {
                    ((it as? Frame.Text)?.readText() ?: "0.0").toDouble()
                } catch (e: NumberFormatException) {
                    Log.e(TAG, "${e.message}")
                    0.0
                }
            }.collectLatest {
                prices.emit(it)
            }

    }

    override fun sendMessage(message: String) {
        runBlocking {
            if(session.isActive)
                session.send(Frame.Text(message))
        }
    }
}