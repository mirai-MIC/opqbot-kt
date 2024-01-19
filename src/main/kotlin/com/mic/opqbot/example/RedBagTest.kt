package com.mic.opqbot.example

import com.mic.opqbot.event.GroupMessageEvent
import com.mic.opqbot.log.MessageLog
import com.mic.opqbot.sender.serivce.SendMessageService
import com.mic.opqbot.util.sendutil
import jakarta.annotation.Resource
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*


@Component
class RedBagTest {

    @Resource
    lateinit var sendMessageService: SendMessageService

    fun RandomThanks() = when (Random().nextInt(4)) {
        0 -> "谢谢老板谢谢老板谢谢老板，给你磕头"
        1 -> "wcwcwc刚进来就有红包吗"
        2 -> "发红包的最帅，爱你爱你爱你"
        3 -> "抢了这么多吗"
        else -> {
            "终于抢到了一次呜呜呜呜呜，谢谢老板"
        }
    }

    @Async
    @EventListener
    fun quickRedBag(event: GroupMessageEvent) {
        val redBag = event.getMessages()?.redBag ?: return
        val redType = redBag.redType
        if (redType != 6 && redType != 12) return
        Thread.sleep(1000)
        val sendMessage = sendMessageService.sendMessage(sendutil.openRedBag(event))
        val money = sendMessage?.get("ResponseData")?.asJsonObject?.get("GetMoney")?.asDouble
        when (redType) {
            6 -> sendMessageService.sendMessage(sendutil.sendMsg(event.getInfo()?.groupCode, RandomThanks(), null))
            12 -> sendMessageService.sendMessage(sendutil.sendMsg(event.getInfo()?.groupCode, redBag.wishing, null))
            else -> return
        }
        MessageLog.info("红包金额： ${money?.div(100)}")
    }
}