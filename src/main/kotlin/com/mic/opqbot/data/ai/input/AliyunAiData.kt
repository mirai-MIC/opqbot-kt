package com.mic.opqbot.data.ai.input


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AliyunAiData(
    @SerializedName("input")
    @Expose
    val input: Input?,
    @SerializedName("model")
    @Expose
    val model: String?, // qwen-turbo
    @SerializedName("parameters")
    @Expose
    val parameters: Parameters?
)