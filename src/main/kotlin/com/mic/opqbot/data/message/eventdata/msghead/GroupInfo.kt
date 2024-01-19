package com.mic.opqbot.data.message.eventdata.msghead


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupInfo(
    @SerializedName("GroupCard")
    @Expose
    val groupCard: String?, // 随薪所欲
    @SerializedName("GroupCode")
    @Expose
    val groupCode: Long?, // 956693142
    @SerializedName("GroupInfoSeq")
    @Expose
    val groupInfoSeq: Int?, // 2870
    @SerializedName("GroupLevel")
    @Expose
    val groupLevel: Int?, // 5
    @SerializedName("GroupName")
    @Expose
    val groupName: String?, // 相亲相爱一家人
    @SerializedName("GroupRank")
    @Expose
    val groupRank: Int?, // 1
    @SerializedName("GroupType")
    @Expose
    val groupType: Int? // 1
)