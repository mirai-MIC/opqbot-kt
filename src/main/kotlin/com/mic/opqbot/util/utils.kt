package com.mic.opqbot.util

object utils {
    data class ImagesData(
        val FileId: Long,
        val FileMd5: String,
        val FileSize: Long,
    )

    //    "Voice": {
//      "FileMd5": "fk5AXTZkLcEp8tK0jGINgQ==",
//      "FileSize": 47121,
//      "FileToken": "9Ai1UvpkGg0agwBve1knSgmb1DyYijbzyRgw"
//    }
    data class VoiceData(
        val FileMd5: String,
        val FileSize: Long,
        val FileToken: String
    )

    object MsgType {
        val Images = "Images"
        val Voice = "Voice"
    }
}