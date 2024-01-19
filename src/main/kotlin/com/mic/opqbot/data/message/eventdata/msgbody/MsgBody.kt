package com.mic.opqbot.data.message.eventdata.msgbody


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Msg body
 * 消息体
 *
 * @property atUinLists
 * @property content
 * @property file
 * @property images
 * @property redBag
 * @property subMsgType
 * @property video
 * @property voice
 * @constructor Create empty Msg body
 */
data class MsgBody(
    /**
     * At对象<List>
     */
    @SerializedName("AtUinLists")
    @Expose
    val atUinLists: List<AtUinList>?, // null
    @SerializedName("Content")
    /**
     *  消息内容 String
     */
    @Expose
    val content: String?, // 只能说
    /**
     * 文件名
     */
    @SerializedName("File")
    @Expose
    val `file`: File?, // null
    /**
     * 图片 <List>
     */
    @SerializedName("Images")
    @Expose
    val images: List<Images>?, // null
    /**
     * 收到红包消息
     */
    @SerializedName("RedBag")
    @Expose
    val redBag: RedBag?, // null
    @SerializedName("SubMsgType")
    @Expose
    val subMsgType: Int?, // 0
    /**
     * 视频消息
     */
    @SerializedName("Video")
    @Expose
    val video: Any?, // null
    @SerializedName("Voice")
    @Expose
    val voice: Any? // null
)