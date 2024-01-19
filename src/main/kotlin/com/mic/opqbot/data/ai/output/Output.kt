package com.mic.opqbot.data.ai.output


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Output(
    @SerializedName("finish_reason")
    @Expose
    val finishReason: String?, // stop
    @SerializedName("text")
    @Expose
    val text: String? // 你好！有什么我可以帮助你的吗？
)