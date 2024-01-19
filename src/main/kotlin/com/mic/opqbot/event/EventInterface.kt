package com.mic.opqbot.event

interface EventInterface {
    fun getMessages(): Any?
    fun getSender(): Any?
    fun getInfo(): Any?
    fun getUserInfo(): Any?
    fun getMsgInfo(): Any?
    fun getBot(): Any?
}