package com.mic.opqbot.data.ai.output


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Usage(
    @SerializedName("input_tokens")
    @Expose
    val inputTokens: Int?, // 8
    @SerializedName("output_tokens")
    @Expose
    val outputTokens: Int?, // 8
    @SerializedName("total_tokens")
    @Expose
    val totalTokens: Int? // 16
)