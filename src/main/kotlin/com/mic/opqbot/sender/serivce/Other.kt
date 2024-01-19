package com.mic.opqbot.sender.serivce

import com.dtflys.forest.annotation.*
import com.mic.opqbot.data.ai.output.OutText
import lombok.SneakyThrows
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component

@Component
@ComponentScan
@BaseRequest(
    timeout = 10000,
    connectTimeout = 10000,
    readTimeout = 10000,
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

}