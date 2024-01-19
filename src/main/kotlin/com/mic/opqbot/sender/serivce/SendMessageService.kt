package com.mic.opqbot.sender.serivce

import com.dtflys.forest.annotation.BaseRequest
import com.dtflys.forest.annotation.JSONBody
import com.dtflys.forest.annotation.Post
import com.google.gson.JsonObject
import com.mic.opqbot.util.query.QueryJson
import lombok.SneakyThrows
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component


@Component
@ComponentScan
@BaseRequest(
    baseURL = "\${baseApi}/v1",
//    timeout =  10000,
//    connectTimeout = 10000,
//    readTimeout = 10000,
)
interface SendMessageService {

    @Post("/LuaApiCaller?funcname=MagicCgiCmd&timeout=10&qq=\${qq}")
    @SneakyThrows
    fun sendMessage(@JSONBody data: Any?): JsonObject?


    @Post("/LuaApiCaller?funcname=MagicCgiCmd&timeout=10&qq=\${qq}")
    @SneakyThrows
    fun queryByUid(@JSONBody data: Any?): QueryJson?


    @Post("/upload?qq=\${qq}")
    @SneakyThrows
    fun upLoadFile(@JSONBody data: Any?): JsonObject?
}