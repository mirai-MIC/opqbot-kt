package com.mic.opqbot.event

import com.mic.opqbot.data.message.currentpacket.CurrentPacket
import com.mic.opqbot.data.message.currentpacket.EventData
import com.mic.opqbot.data.message.currentpacket.EventJoin
import com.mic.opqbot.data.message.eventdata.msgbody.MsgBody
import com.mic.opqbot.data.message.eventdata.msghead.GroupInfo
import com.mic.opqbot.data.message.eventdata.msghead.MsgInfo
import com.mic.opqbot.data.message.eventdata.msghead.Sender
import com.mic.opqbot.data.message.eventdata.msghead.UserInfo
import lombok.Getter
import org.springframework.context.ApplicationEvent

@Getter
class GroupMessageEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!), EventInterface {

    private val message: CurrentPacket? = msgBodyVO

    private val eventData: EventData? = message?.currentPacket?.eventData

    fun eventName(): String {
        return message?.currentPacket?.eventName.toString()
    }

    /**
     * 消息链，包含接收到的消息元素
     * @return
     */
    override fun getMessages(): MsgBody? {
        return eventData?.msgBody
    }

    override fun getSender(): Sender? {
        return eventData?.msgHead?.getSenderUser()
    }

    override fun getInfo(): GroupInfo? {
        return eventData?.msgHead?.groupInfo
    }

    override fun getUserInfo(): UserInfo? {
        return eventData?.msgHead?.getUserInfo()
    }

    override fun getMsgInfo(): MsgInfo? {
        return eventData?.msgHead?.getMsgInfo()
    }

    override fun getBot(): Long? {
        return message?.currentQQ
    }

    fun getToString(): String {
        return message.toString()
    }
}

class GroupJoinEvent(source: Any?, msgBodyVO: CurrentPacket?) : ApplicationEvent(source!!) {

    private val message: CurrentPacket? = msgBodyVO
//消息链: CurrentPacket(currentPacket=CurrentPacketX(eventData=EventData(
// event={AdminUid=u_OWoCswoPQ9myb8wd8DfnMg, Uid=u_NzLC85cSbUHlTJ8X82wVRQ}, msgBody=null,
// msgHead=MsgHead(c2CTempMessageHead=null, c2cCmd=0, fromType=2, fromUid=592501041, fromUin=592501041, groupInfo=null, msgRandom=0, msgSeq=14610, msgTime=1705557878, msgType=33, msgUid=144115188080508961, senderNick=, senderUid=592501041, senderUin=592501041, toUid=u_lwAlRGqNzCm89tWkZZCNZQ, toUin=257207868)), eventName=ON_EVENT_GROUP_JOIN), currentQQ=257207868)

    private val eventData: EventData? = message?.currentPacket?.eventData

    fun getGroupCode(): Long? {
        return eventData?.msgHead?.getUserInfo()?.fromUid?.toLong()
    }

    fun getUser(): EventJoin? {
        return eventData?.event
    }
}