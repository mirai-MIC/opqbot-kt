package com.mic.opqbot.example

import com.google.common.util.concurrent.RateLimiter
import com.google.gson.Gson
import com.mic.opqbot.data.ai.input.AliyunAiData
import com.mic.opqbot.data.ai.input.Input
import com.mic.opqbot.data.ai.input.Message
import com.mic.opqbot.data.message.eventdata.msgbody.AtUinLists
import com.mic.opqbot.event.GroupJoinEvent
import com.mic.opqbot.event.GroupMessageEvent
import com.mic.opqbot.log.MessageLog
import com.mic.opqbot.sender.serivce.Other
import com.mic.opqbot.sender.serivce.SendMessageService
import com.mic.opqbot.util.sendutil
import com.mic.opqbot.util.utils
import jakarta.annotation.Resource
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

    fun randomText() = when (Random.nextInt(3)) {
        0 -> other.getIw23Random()
        1 -> other.getAcg()
        2 -> other.getLoli()
        3 -> other.getSretna()
        else -> other.getAcg()
    }


    @Async
    @EventListener
    fun ms(event: GroupMessageEvent) {
        if (event.getSender()?.uin == event.getBot()) return
        val sender = event.getSender()
        val info = event.getInfo()
        val messages = event.getMessages()
        MessageLog.info("code: ${event.getMsgInfo()?.msgType.toString()}")
        MessageLog.info("eventName: ${event.eventName()}")
        MessageLog.info("群号：${info?.groupCode} - (${info?.groupName}) 群成员：${sender?.uin} - (${sender?.nick})")

        if (messages != null && messages.content?.isNotBlank() == true) MessageLog.info("消息：${messages.content}")
        if (messages?.images != null) messages.images.stream()
            .map { "图片链接->: ${it.url}" }
            .forEach { MessageLog.info(it) }
        MessageLog.info("消息链: ${event.getMessages()}")
    }


    @Async
    @EventListener
    fun revoke(event: GroupMessageEvent) {
        val uin = event.getSender()?.uin
        val bot = event.getBot()
        if (uin != bot) return
        val msgInfo = event.getMsgInfo()
        val msgSeq = msgInfo?.msgSeq
        val msgRandom = msgInfo?.msgRandom
        Thread.sleep(60000)
        MessageLog.info("撤回消息")
        sendMessageService.sendMessage(sendutil.groupRevokeMsg(event.getInfo()?.groupCode, msgSeq, msgRandom))
    }


    @Async
    @EventListener
    fun b(event: GroupJoinEvent) {
        val groupCode = event.getGroupCode()
        val getuid = event.getUser()?.getUids()
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
    @EventListener
    fun Ai(event: GroupMessageEvent) {
        if (event.getMessages()?.atUinLists == null) return
        val bot = event.getMessages()?.atUinLists?.get(0)?.uin
        if (event.getBot() != bot) return
        val aliyunAiData = AliyunAiData(
            model = "qwen-turbo",
            input = Input(listOf(Message(role = "user", content = event.getMessages()?.content))),
            parameters = null
        )
        val aliBot = other.AliBot(Gson().toJson(aliyunAiData)) ?: return
        val nick = event.getSender()?.nick
        val uin = event.getSender()?.uin
        val sendMsg = sendutil.sendMsg(
            event.getInfo()?.groupCode,
            aliBot.output?.text,
            AtUinLists(nick, uin)
        )
        sendMessageService.sendMessage(sendMsg)
    }

    @Async
    @EventListener
    fun asimage(event: GroupMessageEvent) {
        if (!sendutil.MessageEquals(event, "/image")) return

        if (!rateLimiter.tryAcquire()) return
        val sendMessage =
            sendMessageService.sendMessage(
                sendutil.upLoadFile(
                    sendutil.compressAndEncodeToBase64Thumbnails(randomText(), 0.9),
                    sendutil.Type.Base64Buf,
                    sendutil.UploadType.GroupImage
                )
            )

        val response = sendMessage?.get("ResponseData")?.asJsonObject
        val sendMsg = sendutil.sendMsg(
            event.getInfo()?.groupCode!!,
            utils.MsgType.Images,
            utils.ImagesData(
                FileId = response?.get("FileId")?.asLong!!,
                FileMd5 = response.get("FileMd5")?.asString!!,
                FileSize = response.get("FileSize")?.asLong!!
            )
        )
        sendMessageService.sendMessage(sendMsg)
    }


//    @Async
//    @EventListener
//    fun sendVoice(event: GroupMessageEvent) {
//        val processSongCommand = processSongCommand(event.getMessages()?.content!!, "^/点歌\\s(.+)$") ?: return
//
//        val asString = other.getVoice(processSongCommand)?.get("url")?.asString!!
//        val voiceBase64 = other.getVoiceBase64(asString)
//        val sendMessage = sendMessageService.sendMessage(
//            sendutil.upLoadFile(
//                voiceBase64,
//                sendutil.Type.Base64Buf,
//                sendutil.UploadType.GroupVoice
//            )
//        )
//        val response = sendMessage?.get("ResponseData")?.asJsonObject
//
//        println(response)
//        val sendMsg = sendutil.sendMsg(
//            event.getInfo()?.groupCode!!,
//            utils.MsgType.Voice,
//            utils.VoiceData(
//                FileMd5 = response?.get("FileMd5")?.asString!!,
//                FileSize = response.get("FileSize")?.asLong!!,
//                FileToken = response.get("FileToken")?.asString!!,
//            )
//        )
//        sendMessageService.sendMessage(sendMsg)
//
//    }

    fun processSongCommand(inputText: String, regex: String): String? {
        val matchResult = Regex(regex).find(inputText)
        return matchResult?.groupValues?.getOrNull(1)
    }
}