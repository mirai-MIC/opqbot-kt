package com.mic.opqbot.example

import com.google.common.util.concurrent.RateLimiter
import com.google.gson.Gson
import com.mic.opqbot.data.ai.input.AliyunAiData
import com.mic.opqbot.data.ai.input.Input
import com.mic.opqbot.data.message.eventdata.msgbody.AtUinLists
import com.mic.opqbot.event.*
import com.mic.opqbot.log.MessageLog
import com.mic.opqbot.sender.serivce.Other
import com.mic.opqbot.sender.serivce.SendMessageService
import com.mic.opqbot.util.sendutil
import com.mic.opqbot.util.sendutil.messageEquals
import com.mic.opqbot.util.sendutil.regularProcessing
import com.mic.opqbot.util.utils
import jakarta.annotation.Resource
import lombok.SneakyThrows
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class Test {

    @Resource
    lateinit var sendMessageService: SendMessageService

    private val rateLimiter = RateLimiter.create(0.2) // 限制为每秒0.3次

    @Resource
    lateinit var other: Other

    fun randomImage() = when (Random.nextInt(3)) {
        0 -> other.getIw23Random()
        1 -> other.getAcg()
        2 -> other.getLoli()
        3 -> other.getSretna()
        else -> other.getAcg()
    }


    @Async
    @SneakyThrows
    @EventListener
    fun groupMessageOutput(event: GroupMessageEvent) {
        if (event.isFromBot()) return
        val sender = event.getSenderInfo()
        val info = event.getGroupInfo()
        val messages = event.getMessages()
        println()
        MessageLog.info("code: ${event.getMsgTimeInfo()?.msgType.toString()}")
        MessageLog.info("eventName: ${event.getEventName()}")
        MessageLog.info("群号：${info?.groupCode} - (${info?.groupName}) 群成员：${sender?.uin} - (${sender?.nick})")

        if (messages != null && messages.content?.isNotBlank() == true) MessageLog.info("消息：${messages.content}")
        if (messages?.images != null) messages.images.stream()
            .map { "图片链接->: ${it.url}" }
            .forEach { MessageLog.info(it) }
        MessageLog.info("消息链: ${event.getMessages()}")
    }

    @Async
    @EventListener
    fun privateMessageOutPut(event: PrivateMessageEvent) {
        val messages = event.getMessages()
        val json = sendMessageService.queryByUid(sendutil.queryUinByUid(event.getSenderInfo()!!.uid))
        val uidList = sendutil.getUidList(json!!)
        println()
        if (uidList!!.uin == null) return
        MessageLog.info("成员: ${uidList!!.uin}-(${uidList.nick})")
        if (messages != null && messages.content?.isNotBlank() == true) MessageLog.info("消息：${messages.content}")
        if (messages?.images != null) messages.images.stream()
            .map { "图片链接->: ${it.url}" }
            .forEach { MessageLog.info(it) }
        MessageLog.info("消息链: ${event.getMessages()}")

    }

    @Async
    @EventListener
    fun ass(event: GroupInviteEvent) {
        val eventGroupAction = event.getEventGroupAction()
        println()
        MessageLog.info("被邀请人: ${eventGroupAction?.invitee}")
        MessageLog.info("邀请人: ${eventGroupAction?.invitor}")
        MessageLog.info("详细: ${eventGroupAction?.tips}")
//        val sendMsg = sendutil.sendMsg(
//            groupCode = event.getGroupCode()!!,
//            message = "${eventGroupAction?.invitee}被${eventGroupAction?.invitor}邀请进群",
//            atUinList = null
//        )
//        sendMessageService.sendMessage(sendMsg)
    }

    @Async
    @EventListener
    fun exit(event: GroupExitEvent) {
        val json = sendMessageService.queryByUid(sendutil.queryUinByUid(event.getExitUid()))
        val uidList = sendutil.getUidList(json!!)
        println()
        MessageLog.info("群:${event.getGroupCode()} 成员: ${uidList?.uin} ${uidList?.nick} 退群了")
        val sendMsg = sendutil.sendMsg(
            groupCode = event.getGroupCode()!!,
            message = "就在刚刚..我们的群友: ${uidList?.uin}(${uidList?.nick})神隐嘞..",
            atUinList = null
        )
        sendMessageService.sendMessage(sendMsg)
    }


//    @Async
//    @SneakyThrows
//    @EventListener
//    fun revoke(event: GroupMessageEvent) {
//        val uin = event.getSenderInfo()?.uin
//        if (!event.isFromBot()) return
//        val msgInfo = event.getMsgTimeInfo()
//        val msgSeq = msgInfo?.msgSeq
//        val msgRandom = msgInfo?.msgRandom
//        Thread.sleep(60000)
//        MessageLog.info("撤回消息")
//        sendMessageService.sendMessage(sendutil.groupRevokeMsg(event.getGroupCode(), msgSeq, msgRandom))
//    }
//

    @Async
    @SneakyThrows
    @EventListener
    fun addGroupTips(event: GroupJoinJoinEvent) {
        val groupCode = event.getGroupCode()
        val getuid = event.getEventGroupAction()?.uid
        val queryByUid = sendMessageService.queryByUid(sendutil.queryUinByUid(getuid))
        val uidList = sendutil.getUidList(queryByUid!!)
        val sendMsg = sendutil.sendMsg(
            groupCode = groupCode!!,
            message = " 欢迎大佬!!!",
            atUinList = AtUinLists(uidList?.nick, uidList?.uin)
        )
        sendMessageService.sendMessage(sendMsg)
    }

    @Async
    @SneakyThrows
    @EventListener
    fun aiQA(event: GroupMessageEvent) {
        if (event.isFromBot()) return
        if (!event.atBot()) return
        val removeAtPrefix = removeAtPrefix(event.getTextContent()!!)
        val aliyunAiData = AliyunAiData(
            model = "qwen-max",
            input = Input(
                sendutil.aiModel(removeAtPrefix)
            ),
            parameters = null
        )
        val aliBot = other.AliBot(Gson().toJson(aliyunAiData)) ?: return
        val nick = event.getSenderInfo()?.nick
        val uin = event.getSenderInfo()?.uin
        val sendMsg = sendutil.sendMsg(
            event.getGroupCode(),
            aliBot.output?.text,
            AtUinLists(nick, uin)
        )
        sendMessageService.sendMessage(sendMsg)
    }

    @Async
    @EventListener
    fun aiPrivate(event: PrivateMessageEvent) {
        if (event.getTextContent() == null) return
        if (event.isFormBot()) return
        if (event.getTextContent()!!.isEmpty()) return
        val aliyunAiData = AliyunAiData(
            model = "qwen-max",
            input = Input(
                sendutil.aiModel(event.getTextContent())
            ),
            parameters = null
        )
        val aliBot = other.AliBot(Gson().toJson(aliyunAiData)) ?: return

        val sendMsg = sendutil.sendMsg(event.getSenderInfo()!!.uin!!, aliBot.output!!.text!!)
        sendMessageService.sendMessage(sendMsg)
    }

    @Async
    @SneakyThrows
    @EventListener
    fun sendPictures(event: GroupMessageEvent) {
        if (!"/image".messageEquals(event)) return
        if (!rateLimiter.tryAcquire()) return
        val sendMessage =
            sendMessageService.sendMessage(
                sendutil.upLoadFile(
                    sendutil.compressAndEncodeToBase64Thumbnails(randomImage(), 0.9),
                    sendutil.Type.Base64Buf,
                    sendutil.UploadType.GroupImage
                )
            )
        val response = sendMessage?.get("ResponseData")?.asJsonObject
        val sendMsg = sendutil.sendMsg(
            event.getGroupCode()!!,
            utils.MsgType.Images,
            utils.ImagesData(
                FileId = response?.get("FileId")?.asLong!!,
                FileMd5 = response.get("FileMd5")?.asString!!,
                FileSize = response.get("FileSize")?.asLong!!
            )
        )
        sendMessageService.sendMessage(sendMsg)
    }


    @Async
    @SneakyThrows(Exception::class)
    @EventListener
    fun sendVoice(event: GroupMessageEvent) {
        val processSongCommand = event.getMessages()?.content!!.regularProcessing("^/点歌\\s(.+)$") ?: return
        val asString = other.getVoice(processSongCommand)?.get("url")?.asString!!
        val voiceBase64 = other.getVoiceBase64(asString)
        val sendMessage = sendMessageService.upLoadFile(
            sendutil.upLoadFile(
                voiceBase64,
                sendutil.Type.Base64Buf,
                sendutil.UploadType.GroupVoice
            )
        )
        val response = sendMessage?.get("ResponseData")?.asJsonObject

        println(response)
        val sendMsg = sendutil.sendMsg(
            event.getGroupCode()!!,
            utils.MsgType.Voice,
            utils.VoiceData(
                FileMd5 = response?.get("FileMd5")?.asString!!,
                FileSize = response.get("FileSize")?.asLong!!,
                FileToken = response.get("FileToken")?.asString!!,
            )
        )
        sendMessageService.sendMessage(sendMsg)

    }

    fun removeAtPrefix(text: String): String {
        return text.substring(9)
    }
}