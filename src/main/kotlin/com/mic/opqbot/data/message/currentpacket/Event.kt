package com.mic.opqbot.data.message.currentpacket


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mic.opqbot.data.message.eventdata.msghead.EventAction
import com.mic.opqbot.data.message.eventdata.msghead.InviteInfo


data class EventGroupAction(

    @SerializedName("AdminUid") @Expose val adminUid: String?, // u_OWoCswoPQ9myb8wd8DfnMg
    @SerializedName("Uid") @Expose val uid: String?,// u_NzLC85cSbUHlTJ8X82wVRQ

    @SerializedName("Invitee") @Expose val invitee: Long?, //"123456789",  //被邀请人Uin

    @SerializedName("Invitor") @Expose val invitor: Long?, //"987654321",

    @SerializedName("Tips") @Expose val tips: String? //"邀请你加入群聊"
) {


    /**
     * 退群者uids
     * @return
     */
    fun getUids(): String {
        return uid.toString()
    }


    /**
     * @param adminUid  同意者id
     * @param uid  进去者id
     * @return EventAction
     */
    fun getAdminUidAndUidData(): EventAction {
        return EventAction(adminUid.toString(), uid.toString())
    }

    /**
     * 群成员邀请事件
     * @param Invitee 被邀请人
     * @param  Invitor 邀请人
     * @param  tips  详细消息
     * @return InviteInfo
     */
    fun getInviteInfo(): InviteInfo {
        return InviteInfo(invitee, invitor, tips)
    }

}
