package com.mic.opqbot.util.query


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QueryJson(
    @SerializedName("CgiBaseResponse")
    @Expose
    val cgiBaseResponse: CgiBaseResponse?,
    @SerializedName("ResponseData")
    @Expose
    val responseData: List<ResponseData?>?
)