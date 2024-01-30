package com.mic.opqbot.event

import com.mic.opqbot.data.message.eventdata.msgbody.AtUinList
import com.mic.opqbot.data.message.eventdata.msgbody.MsgBody
import com.mic.opqbot.data.message.eventdata.msghead.*

/**
 * 群聊消息接口
 */
interface EventGroupMessageInterfaceInterface : EventCommonMsgTimeInterface, EventMessages, EventUserInterface,
    EventCommonGroupInterface {
    fun atBot(): Boolean
    fun getAtInfo(): List<AtUinList>?
    fun getGroupInfo(): GroupInfo?
    fun containedPic(): Boolean
    fun containedAt(): Boolean
    fun isFromBot(): Boolean

}

/**
 * 成员加群接口
 */
interface EventGroupJoinInterface : EventCommonGroupInterface, EventCommonMsgTimeInterface, EventUserInterface,
    EventGroupActionInterface

/**
 * 成员退群接口
 */
interface EventGroupExitInterface : EventCommonGroupInterface, EventCommonMsgTimeInterface, EventUserInterface

interface EventGroupInviteInterface : EventCommonGroupInterface, EventCommonMsgTimeInterface, EventUserInterface,
    EventGroupActionInterface

interface EventPrivateMessageInterface : EventCommonMsgTimeInterface, EventUserInterface, EventMessages


interface EventGroupActionInterface {
    fun getEventGroupAction(): Any?
}

/**
 * 群号公共接口
 */
interface EventCommonGroupInterface {
    fun getGroupCode(): Long?
}

/**
 * 事件时间戳公共接口
 */
interface EventCommonMsgTimeInterface {
    fun getMsgTimeInfo(): MsgInfo?
    fun getSenderInfo(): Sender?
    fun getEventName(): Any?
}

/**
 * User公共接口
 */
interface EventUserInterface {
    fun isFromInfo(): FromInfo?
    fun isToInfo(): ToInfo?
}

/**
 * 文本消息公共接口
 */
interface EventMessages {
    fun getTextContent(): String?
    fun getMessages(): MsgBody?
}