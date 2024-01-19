package com.mic.opqbot.data.ai.output


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OutText(
    @SerializedName("output")
    @Expose
    val output: Output?,
    @SerializedName("request_id")
    @Expose
    val requestId: String?, // 9b5b6c93-e63a-9e81-ba13-5664cf3efff4
    @SerializedName("usage")
    @Expose
    val usage: Usage?
)