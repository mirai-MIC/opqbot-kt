package com.mic.opqbot.data.message.eventdata.msgbody


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("FileId")
    @Expose
    val fileId: Long?, // 1799892739
    @SerializedName("FileMd5")
    @Expose
    val fileMd5: String?, // m8LnEJiF4uODRgDNi0e2SQ==
    @SerializedName("FileSize")
    @Expose
    val fileSize: Long?, // 8604
    @SerializedName("Height")
    @Expose
    val height: Int?, // 300
    @SerializedName("Url")
    @Expose
    val url: String?, // http://gchat.qpic.cn/download?appid=1407&fileid=CgoyODQ2MDIxNTY2EhS-4_m4f-qauB6xprhOFiztVNC8UxicQyD_CiiV-LG5suODA1CAvaMB&rkey=CAMSKMa3OFokB_TlEc7qE0x_uKmsK4FibfmzXee4pCmZcPpEOy2_TXFPlUM&spec=0
    @SerializedName("Width")
    @Expose
    val width: Int? // 300
)