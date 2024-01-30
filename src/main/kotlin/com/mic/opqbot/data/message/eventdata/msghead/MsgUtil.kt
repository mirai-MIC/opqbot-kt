package com.mic.opqbot.data.message.eventdata.msghead

import lombok.Data

class MsgUtil

@Data
data class Sender(
    val nick: String?,
    val uin: Long?,
    val uid: String?
)

@Data
data class MsgInfo(
    val msgRandom: Long?,
    val msgSeq: Long?,
    val msgTime: Long?,
    val msgUid: Long?,
    val msgType: Int?
)

@Data
data class FromInfo(
    val fromType: Int?, // 1
    val fromUin: Long?,
    val fromUid: String?,
)

@Data
data class ToInfo(
    val toUid: String?,
    val toUin: Long?
)

@Data
data class InviteInfo(
    val invitee: Long?,
    val invitor: Long?,
    val tips: String?
)
//    @SerializedName("AdminUid") @Expose val adminUid: String?, // u_OWoCswoPQ9myb8wd8DfnMg
//    @SerializedName("Uid") @Expose val uid: String?,// u_NzLC85cSbUHlTJ8X82wVRQ

@Data
data class EventAction(
    val adminUid: String?,
    val uid: String?
)
