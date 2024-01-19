package com.mic.opqbot.data.message.eventdata.msgbody


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AtUinList(
    @SerializedName("Nick")
    @Expose
    val nick: String?, // (ᵔᵕᵔ˶)
    @SerializedName("Uid")
    @Expose
    val uid: String?, // u_lwAlRGqNzCm89tWkZZCNZQ
    @SerializedName("Uin")
    @Expose
    val uin: Long? // 257207868
)