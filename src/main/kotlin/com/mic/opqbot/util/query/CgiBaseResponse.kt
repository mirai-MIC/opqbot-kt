package com.mic.opqbot.util.query


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CgiBaseResponse(
    @SerializedName("ErrMsg")
    @Expose
    val errMsg: String?,
    @SerializedName("Ret")
    @Expose
    val ret: Int? // 0
)