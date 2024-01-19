package com.mic.opqbot.data.message.eventdata.msghead


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MsgHead(
    @SerializedName("C2CTempMessageHead")
    @Expose
    val c2CTempMessageHead: Any?, // null
    @SerializedName("C2cCmd")
    @Expose
    val c2cCmd: Int?, // 0
    @SerializedName("FromType")
    @Expose
    val fromType: Int?, // 2
    @SerializedName("FromUid")
    @Expose
    val fromUid: String?,
    @SerializedName("FromUin")
    @Expose
    val fromUin: Long?, // 956693142
    @SerializedName("GroupInfo")
    @Expose
    val groupInfo: GroupInfo?,
    @SerializedName("MsgRandom")
    @Expose
    val msgRandom: Long?, // 700470997
    @SerializedName("MsgSeq")
    @Expose
    val msgSeq: Long?, // 321915
    @SerializedName("MsgTime")
    @Expose
    val msgTime: Long?, // 1705408291
    @SerializedName("MsgType")
    @Expose
    val msgType: Int?, // 82
    @SerializedName("MsgUid")
    @Expose
    val msgUid: Long?, // 72057594738398933
    @SerializedName("SenderNick")
    @Expose
    val senderNick: String?, // 随薪所欲
    @SerializedName("SenderUid")
    @Expose
    val senderUid: String?, // u_hZO7JVBCmykl7Ew_yxefQQ
    @SerializedName("SenderUin")
    @Expose
    val senderUin: Long?, // 2573268985
    @SerializedName("ToUid")
    @Expose
    val toUid: String?,
    @SerializedName("ToUin")
    @Expose
    val toUin: Long? // 257207868
) {
    /**
     * 消息发送者info
     * @return
     */
    fun getSenderUser(): Sender {
        return Sender(nick = senderNick, uin = senderUin, uid = senderUid)
    }

    /**
     * 包含消息发送的时间记录,消息Id，消息类型
     *  @return
     */
    fun getMsgInfo(): MsgInfo {
        return MsgInfo(msgRandom = msgRandom, msgSeq = msgSeq, msgTime = msgTime, msgUid = msgUid, msgType = msgType)
    }

    /**
     * 封装发送消息体中from和to部分
     *
     * @return
     */
    fun getUserInfo(): UserInfo {
        return UserInfo(fromType = fromType, fromUid = fromUid, fromUin = fromUin, toUid = toUid, toUin = toUin)
    }
}



