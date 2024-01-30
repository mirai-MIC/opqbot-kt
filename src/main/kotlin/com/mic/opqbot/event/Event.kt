package com.mic.opqbot.event

import com.mic.opqbot.data.message.currentpacket.CurrentPacket
import com.mic.opqbot.data.message.currentpacket.EventData
import com.mic.opqbot.data.message.eventdata.msgbody.AtUinList
import com.mic.opqbot.data.message.eventdata.msgbody.MsgBody
import com.mic.opqbot.data.message.eventdata.msghead.*
import org.springframework.context.ApplicationEvent


/**
 * 群聊消息类
 * @property message CurrentPacket?
 * @property eventData EventData?
 * @constructor
 */

class GroupMessageEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!),
    EventGroupMessageInterfaceInterface {

    private val message: CurrentPacket? = msgBodyVO

    private val eventData: EventData? = message?.currentPacket?.eventData
    override fun atBot(): Boolean {
        return getAtInfo()?.any { it.uin == message!!.currentQQ } ?: false
    }

    override fun getAtInfo(): List<AtUinList>? {
        return eventData!!.msgBody?.atUinLists
    }

    override fun getGroupCode(): Long? {
        return eventData!!.msgHead?.groupInfo?.groupCode
    }

    override fun getGroupInfo(): GroupInfo? {
        return eventData!!.msgHead?.groupInfo
    }

    override fun getSenderInfo(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getEventName(): Any? {
        return message!!.currentPacket!!.eventName
    }

    override fun containedPic(): Boolean {
        return getMessages()!!.images!!.isEmpty()
    }

    override fun containedAt(): Boolean {
        return getMessages()!!.atUinLists!!.isEmpty()
    }

    override fun isFromBot(): Boolean {
        return getSenderInfo()!!.uin == message!!.currentQQ
    }

    override fun getMessages(): MsgBody? {
        return eventData!!.msgBody
    }

    override fun getMsgTimeInfo(): MsgInfo? {
        return eventData!!.msgHead?.getMsgInfo()
    }


    override fun getTextContent(): String? {
        return getMessages()?.content
    }

    override fun isFromInfo(): FromInfo? {
        return eventData!!.msgHead?.getFromInfo()
    }

    override fun isToInfo(): ToInfo? {
        return eventData!!.msgHead?.getToInfo()
    }

}

/**
 * 成员进群类
 * @property message CurrentPacket?
 * @property eventData EventData?
 * @constructor
 */
class GroupJoinEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!),
    EventGroupJoinInterface {

    private val message: CurrentPacket? = msgBodyVO
    private val eventData: EventData? = message?.currentPacket?.eventData

    override fun getGroupCode(): Long? {
        return isFromInfo()?.fromUin
    }

    override fun getMsgTimeInfo(): MsgInfo? {
        return eventData!!.msgHead?.getMsgInfo()
    }

    override fun getSenderInfo(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getEventName(): Any? {
        return message!!.currentPacket?.eventName
    }


    override fun isFromInfo(): FromInfo? {
        return eventData!!.msgHead?.getFromInfo()
    }

    override fun isToInfo(): ToInfo? {
        return eventData!!.msgHead?.getToInfo()
    }

    override fun getEventGroupAction(): EventAction? {
        return eventData?.event?.getAdminUidAndUidData()
    }
}

/**
 * 成员退群群聊
 * @property message CurrentPacket?
 * @property eventData EventData?
 * @constructor
 */
class GroupExitEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!), EventGroupExitInterface {

    private val message: CurrentPacket? = msgBodyVO
    private val eventData: EventData? = message?.currentPacket?.eventData

    override fun getGroupCode(): Long? {
        return isFromInfo()?.fromUin
    }


    override fun getMsgTimeInfo(): MsgInfo? {
        return eventData!!.msgHead?.getMsgInfo()
    }

    override fun getSenderInfo(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getEventName(): Any? {
        return message!!.currentPacket?.eventName
    }


    override fun isFromInfo(): FromInfo? {
        return eventData!!.msgHead?.getFromInfo()
    }

    override fun isToInfo(): ToInfo? {
        return eventData!!.msgHead?.getToInfo()
    }

    fun getExitUid(): String? {
        return eventData?.event?.getUids()
    }
}

/**
 * 成员邀请事件
 * @property message CurrentPacket?
 * @property eventData EventData?
 * @constructor
 */
class GroupInviteEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!),
    EventGroupInviteInterface {

    private val message: CurrentPacket? = msgBodyVO
    private val eventData: EventData? = message?.currentPacket?.eventData
    override fun getInviteUid(): String? {
        return eventData!!.event?.invite()
    }

    override fun getBeInvitedUid(): String? {
        return eventData!!.event?.beInvited()
    }

    override fun getGroupCode(): Long? {
        return isFromInfo()?.fromUin
    }

    override fun getMsgTimeInfo(): MsgInfo? {
        return eventData!!.msgHead?.getMsgInfo()
    }


    override fun getSenderInfo(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getEventName(): Any? {
        return message!!.currentPacket?.eventName
    }


    override fun isFromInfo(): FromInfo? {
        return eventData!!.msgHead?.getFromInfo()
    }

    override fun isToInfo(): ToInfo? {
        return eventData!!.msgHead?.getToInfo()
    }

    override fun getEventGroupAction(): InviteInfo? {
        return eventData?.event?.getInviteInfo()
    }

}

/**
 * 私聊消息事件
 * @property message CurrentPacket?
 * @property eventData EventData?
 * @constructor
 */
class PrivateMessageEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!),
    EventPrivateMessageInterface {

    private val message: CurrentPacket? = msgBodyVO
    private val eventData: EventData? = message?.currentPacket?.eventData
    fun isFormBot(): Boolean {
        return getSenderInfo()!!.uin == message!!.currentQQ
    }

    override fun getMsgTimeInfo(): MsgInfo? {
        return eventData!!.msgHead?.getMsgInfo()
    }

    override fun getSenderInfo(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getEventName(): Any? {
        return message!!.currentPacket?.eventName
    }

    override fun isFromInfo(): FromInfo? {
        return eventData!!.msgHead?.getFromInfo()
    }

    override fun isToInfo(): ToInfo? {
        return eventData!!.msgHead?.getToInfo()
    }

    override fun getTextContent(): String? {
        return eventData!!.msgBody?.content
    }

    override fun getMessages(): MsgBody? {
        return eventData!!.msgBody
    }

    fun getType(): Boolean? {
        return eventData!!.msgHead?.msgType == 166
    }

}