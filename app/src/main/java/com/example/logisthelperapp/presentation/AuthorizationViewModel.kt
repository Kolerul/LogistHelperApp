package com.example.logisthelperapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logisthelperapp.domain.model.AutorizationData
import com.example.logisthelperapp.domain.model.User
import com.example.logisthelperapp.domain.repository.UserRepository
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    var phoneNumber = MutableLiveData("")
    var password = MutableLiveData("")

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun logIn(){
        val data = AutorizationData(phoneNumber.value!!, password.value!!)
        _user.value = repository.logIn(data)
    }
}