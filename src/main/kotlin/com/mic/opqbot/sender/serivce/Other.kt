package com.mic.opqbot.sender.serivce

import com.dtflys.forest.annotation.*
import com.google.gson.JsonObject
import com.mic.opqbot.data.ai.output.OutText
import lombok.SneakyThrows
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component

@Component
@ComponentScan
@BaseRequest(
    timeout = 100000,
    readTimeout = 100000,
    connectTimeout = 100000,

    )
interface Other {


    @Headers(
        "Authorization: Bearer \${apiKey}", "Content-Type: application/json"
    )
    @Post("https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation")
    fun AliBot(@Body text: String?): OutText?


    @Get("https://tenapi.cn/v2/acg")
    fun getAcg(): ByteArray?

    @Get("https://iw233.cn/api.php?sort=random")
    fun getIw23Random(): ByteArray?

    @Get("https://api.sretna.cn/layout/pc.php")
    @SneakyThrows
    fun getSretna(): ByteArray?

    @Get("https://www.loliapi.com/acg/")
    fun getLoli(): ByteArray?

    @Get("https://xiaoapi.cn/API/yy_sq.php?msg=\${name}&type=json&n=1")
    fun getVoice(@Var("name") name: String): JsonObject?

    @Get("\${url}")
    fun getVoiceBase64(@Var("url") url: String): ByteArray

}