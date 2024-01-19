package com.mic.opqbot.data.message.eventdata.msgbody


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class File(
    @SerializedName("FileName")
    @Expose
    val fileName: String?, // a.txt
    @SerializedName("FileSize")
    @Expose
    val fileSize: Int?, // 88
    @SerializedName("PathId")
    @Expose
    val pathId: String?, // /bb0dd50c-4025-42a2-870a-72d815505843
    @SerializedName("Tips")
    @Expose
    val tips: String? // [群文件]
)