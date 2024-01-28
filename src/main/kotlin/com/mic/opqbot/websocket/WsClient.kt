package com.mic.opqbot.websocket

import com.google.gson.Gson
import com.mic.opqbot.config.YamlConfig
import com.mic.opqbot.data.message.currentpacket.CurrentPacket
import com.mic.opqbot.enums.EventNameType
import com.mic.opqbot.event.GroupJoinEvent
import com.mic.opqbot.event.GroupMessageEvent
import com.mic.opqbot.log.MessageLog
import jakarta.annotation.Resource
import okhttp3.*
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class WsClient {
    @Resource
    lateinit var yamlConfig: YamlConfig
    @Resource
    lateinit var applicationContext: ApplicationContext
    val client = OkHttpClient()

    @Bean(name = ["WsClientBean"])
    fun getConnection() {
        client()
    }


    fun client(): WebSocket {
        val request = Request.Builder().url("ws://${yamlConfig.Ip}/ws").build()

        val listener = object : WebSocketListener() {
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                MessageLog.warn("链接关闭 code: $code , reason: $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                MessageLog.error("连接断开  t: ${t.message} ,response: ${response?.message}")
                Thread.sleep(100000)
                client()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val fromJson = Gson().fromJson(text, CurrentPacket::class.java)
                val eventName = fromJson.currentPacket?.eventName
                when (eventName) {
                    EventNameType.ON_EVENT_GROUP_NEW_MSG -> applicationContext.publishEvent(
                        GroupMessageEvent(
                            this,
                            fromJson
                        )
                    )
                    EventNameType.ON_EVENT_GROUP_JOIN -> applicationContext.publishEvent(
                        GroupJoinEvent(
                            this,
                            fromJson
                        )
                    )
                }
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                MessageLog.info("WebSocket连接已建立")
            }
        }

        return client.newWebSocket(request, listener)
    }
}