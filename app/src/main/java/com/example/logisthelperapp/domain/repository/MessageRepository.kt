package com.example.logisthelperapp.domain.repository

import com.example.logisthelperapp.domain.model.Message

interface MessageRepository {

    fun getAllMessagesFrom(from: String): List<Message>

    fun sendMessage(text: String)

}