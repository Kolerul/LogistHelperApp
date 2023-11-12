package com.example.logisthelperapp.domain.repository

import com.example.logisthelperapp.domain.model.AutorizationData
import com.example.logisthelperapp.domain.model.User

interface UserRepository {
    fun logIn(data: AutorizationData): User
}