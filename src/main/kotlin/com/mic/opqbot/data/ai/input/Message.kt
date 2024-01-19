package com.mic.opqbot.data.ai.input


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("content")
    @Expose
    val content: String?, // You are a helpful assistant.
    @SerializedName("role")
    @Expose
    val role: String? // system
)