package com.mic.opqbot.event

import com.mic.opqbot.data.message.currentpacket.CurrentPacket
import com.mic.opqbot.data.message.currentpacket.EventData
import com.mic.opqbot.data.message.currentpacket.EventJoin
import com.mic.opqbot.data.message.eventdata.msgbody.AtUinList
import com.mic.opqbot.data.message.eventdata.msgbody.MsgBody
import com.mic.opqbot.data.message.eventdata.msghead.*
import lombok.Getter
import org.springframework.context.ApplicationEvent

@Getter
class GroupMessageEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!), EventGroupMsgInterface {

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

    override fun getMsgTime(): MsgInfo? {
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

class GroupJoinEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!), EventJoinGroupInterface {

    private val message: CurrentPacket? = msgBodyVO
    private val eventData: EventData? = message?.currentPacket?.eventData

    override fun getGroupCode(): Long? {
        return isFromInfo()?.fromUin
    }

    override fun getMsgTime(): MsgInfo? {
        return eventData!!.msgHead?.getMsgInfo()
    }

    override fun getSenderInfo(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getEventName(): Any? {
        return message!!.currentPacket?.eventName
    }

    fun getUser(): EventJoin? {
        return eventData?.event
    }

    override fun isFromInfo(): FromInfo? {
        return eventData!!.msgHead?.getFromInfo()
    }

    override fun isToInfo(): ToInfo? {
        return eventData!!.msgHead?.getToInfo()
    }
}