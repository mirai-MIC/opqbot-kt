package com.mic.opqbot.data.ai.input


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Input(
    @SerializedName("messages")
    @Expose
    val messages: List<Message?>?
)