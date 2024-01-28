package com.mic.opqbot.event

import com.mic.opqbot.data.message.eventdata.msgbody.AtUinList
import com.mic.opqbot.data.message.eventdata.msgbody.MsgBody
import com.mic.opqbot.data.message.eventdata.msghead.*

interface EventGroupMsgInterface : EventCommonMsgTimeInterface, ITextMsg, EventUserInterFace, EventCommonGroup {
    fun atBot(): Boolean
    fun getAtInfo(): List<AtUinList>?
    fun getGroupInfo(): GroupInfo?
    fun containedPic(): Boolean
    fun containedAt(): Boolean
    fun isFromBot(): Boolean
    fun getMessages(): MsgBody?
}

interface EventJoinGroupInterface : EventCommonGroup, EventCommonMsgTimeInterface, EventUserInterFace

interface EventCommonGroup {
    fun getGroupCode(): Long?
}

interface EventCommonMsgTimeInterface {
    fun getMsgTime(): MsgInfo?
    fun getSenderInfo(): Sender?
    fun getEventName(): Any?
}

interface EventUserInterFace {
    fun isFromInfo(): FromInfo?
    fun isToInfo(): ToInfo?
}

interface ITextMsg {
    fun getTextContent(): String?
}