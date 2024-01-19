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
    private val eventData: EventData? = message?.currentPacket?.eventData

    fun getGroupCode(): Long? {
        return eventData?.msgHead?.getUserInfo()?.fromUid?.toLong()
    }

    fun getUser(): EventJoin? {
        return eventData?.event
    }
}