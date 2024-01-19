package com.mic.opqbot.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "opqbot")
data class YamlConfig(
    /**
     *  服务器ip
     */
    val Ip: String,
    /**
     *  机器人qq号
     */
    val qid: Long
)
