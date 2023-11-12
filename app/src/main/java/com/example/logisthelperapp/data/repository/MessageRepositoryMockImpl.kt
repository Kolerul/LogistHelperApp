package com.example.logisthelperapp.data.repository

import com.example.logisthelperapp.domain.model.Message
import com.example.logisthelperapp.domain.repository.MessageRepository
import java.util.Calendar
import javax.inject.Inject

class MessageRepositoryMockImpl @Inject constructor(): MessageRepository {
    override fun getAllMessagesFrom(from: String): List<Message> = messages

    override fun sendMessage(text: String) {
        val message = Message(
            text = text,
            time = "${Calendar.HOUR}:${Calendar.MINUTE}",
            author = "You"
        )
        messages.add(message)
    }

    val messages = mutableListOf(
        Message(
            text = "В общем, так. Я кого попало в ученики не беру.",
            time = "16:44",
            author = "You"
        ),
        Message(
            text = "А если я буду не «кого попало»?",
            time = "16:45",
            author = "Elisey"
        ),
        Message(
            text = "Куда попало?",
            time = "16:45",
            author = "You"
        ),
        Message(
            text = "Ну, ты говоришь: «кого попало».",
            time = "16:46",
            author = "Elisey"
        ),
        Message(
            text = "Так, гонец, меньше говори — больше слушай. Глядишь, и научишься чему.",
            time = "16:48",
            author = "You"
        )
    )
}