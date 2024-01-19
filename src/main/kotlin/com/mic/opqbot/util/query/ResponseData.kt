package com.mic.opqbot.util.query


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("Head")
    @Expose
    val head: String?, // http://qh.qlogo.cn/g?b=qq&ek=AQXiaOeI0ia9pQcIKj9gVrDhcSgFHK7d1NJfk3nznwB3bCzBr9L4oicjcwxmUtr2cNoBevsskYRicHOOYNicWzLQq9GU0bHOGJQ8jibsX9Eibia2mcsM0xY8Sf9uNhAQncibtJA&s=
    @SerializedName("Level")
    @Expose
    val level: Int?, // 51
    @SerializedName("Mark")
    @Expose
    val mark: String?,
    @SerializedName("Nick")
    @Expose
    val nick: String?, // 白字儿杠精
    @SerializedName("Sex")
    @Expose
    val sex: Int?, // 255
    @SerializedName("Signature")
    @Expose
    val signature: String?,
    @SerializedName("Uid")
    @Expose
    val uid: String?, // u_NzLC85cSbUHlTJ8X82wVRQ
    @SerializedName("Uin")
    @Expose
    val uin: Long? // 2206483061
)