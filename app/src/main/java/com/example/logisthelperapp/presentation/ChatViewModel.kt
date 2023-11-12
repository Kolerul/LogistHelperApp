package com.example.logisthelperapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logisthelperapp.domain.model.Message
import com.example.logisthelperapp.domain.repository.MessageRepository
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val repository: MessageRepository
): ViewModel() {

    private val _interlocutor = MutableLiveData("Елисей Игоревич")
    val interlocutor: LiveData<String>
        get() = _interlocutor

    private val _messages = MutableLiveData(repository.getAllMessagesFrom(_interlocutor.value!!))
    val messages: LiveData<List<Message>>
        get() = _messages

    fun sendMessage(text: String){
        repository.sendMessage(text)
        _messages.value = repository.getAllMessagesFrom(_interlocutor.value!!)
    }

}