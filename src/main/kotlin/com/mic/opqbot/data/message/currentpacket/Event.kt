package com.mic.opqbot.data.message.currentpacket


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EventJoin(
    @SerializedName("AdminUid")
    @Expose
    val adminUid: String?, // u_OWoCswoPQ9myb8wd8DfnMg
    @SerializedName("Uid")
    @Expose
    val uid: String? // u_NzLC85cSbUHlTJ8X82wVRQ
) {
    fun getAnotherUid(): String {
        return adminUid.toString()
    }

    fun getUids(): String {
        return uid.toString()
    }


}
