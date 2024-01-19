package com.mic.opqbot.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object MessageLog {
    private val logger: Logger = LoggerFactory.getLogger(MessageLog::class.java)

    fun info(message: String) = logger.info(message)

    fun warn(message: String) = logger.warn(message)

    fun error(message: String) = logger.error(message)
}