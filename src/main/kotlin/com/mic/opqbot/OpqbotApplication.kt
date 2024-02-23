package com.mic.opqbot

import com.mic.opqbot.config.YamlConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(YamlConfig::class)
class OpqbotApplication

fun main(args: Array<String>) =
    runApplication<OpqbotApplication>(*args)
