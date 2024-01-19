package com.mic.opqbot.enums

object EventNameType {
    /**
     * 普通群消息
     */
    val ON_EVENT_GROUP_NEW_MSG = "ON_EVENT_GROUP_NEW_MSG"

    /**
     *  好友消息
     */
    val ON_EVENT_FRIEND_NEW_MSG = "ON_EVENT_FRIEND_NEW_MSG"


    /**
     * 进群消息
     */
    val ON_EVENT_GROUP_JOIN = "ON_EVENT_GROUP_JOIN"

    /**
     * 退群消息
     */
    val ON_EVENT_GROUP_EXIT = "ON_EVENT_GROUP_EXIT"

    /**
     * 邀请事件
     */
    val ON_EVENT_GROUP_INVITE = "ON_EVENT_GROUP_INVITE"

    /**
     * 群组系统消息事件
     *
     *
     * //MsgType 1 申请进群 2 被邀请进群 13退出群聊 15取消管理员 3设置管理员
     *
     *
     * //13退出群聊 针对管理员群主的推送事件
     */
    val ON_EVENT_GROUP_SYSTEM_MSG_NOTIFY = "ON_EVENT_GROUP_SYSTEM_MSG_NOTIFY"
}