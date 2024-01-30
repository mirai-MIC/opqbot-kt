package com.mic.opqbot.data.message.currentpacket


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mic.opqbot.data.message.eventdata.msgbody.MsgBody
import com.mic.opqbot.data.message.eventdata.msghead.MsgHead


data class EventData(
    /**
     * 进群事件
     */
    @SerializedName("Event")
    @Expose
    val event: EventGroupAction?, // null
    /**
     * 消息体
     */
    @SerializedName("MsgBody")
    @Expose
    val msgBody: MsgBody?, // null
    /**
     * 消息头
     */
    @SerializedName("MsgHead")
    @Expose
    val msgHead: MsgHead? // null
) {

}