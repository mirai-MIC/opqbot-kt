package com.mic.opqbot.data.message.eventdata.msgbody


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RedBag(
    @SerializedName("Authkey")
    @Expose
    val authkey: String?, // adc13047feae97e4de368e761d03adb5mq
    @SerializedName("Channel")
    @Expose
    val channel: Int?, // 1
    @SerializedName("Des")
    @Expose
    val des: String?, // 赶紧点击拆开吧
    @SerializedName("FromType")
    @Expose
    val fromType: Int?, // 1
    @SerializedName("FromUin")
    @Expose
    val fromUin: Int?, // 609176978
    @SerializedName("Listid")
    @Expose
    val listid: String?, // 10000448012401173500100264701800
    @SerializedName("RedType")
    @Expose
    val redType: Int?, // 6
    @SerializedName("StingIndex")
    @Expose
    val stingIndex: String?, // MmQwNDg3ZTAyMDQwYWQxZGUzOWUzOTA3NzAxYTkzYjU=
    @SerializedName("Token_17_2")
    @Expose
    val token172: String?, // 8FdmwiPjzHRBv5jdEAEKqy5cU3L2JmmPTo1cBGgdOHE=
    @SerializedName("Token_17_3")
    @Expose
    val token173: String?, // ODAyNGYyZjliODVlN2ExZGMzOTg3ZDQ1YmU1Y2M1ZjE=
    @SerializedName("TransferMsg")
    @Expose
    val transferMsg: String?,
    @SerializedName("Wishing")
    @Expose
    val wishing: String? // 大吉大利
)