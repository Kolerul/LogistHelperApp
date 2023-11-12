package com.example.logisthelperapp.domain.model

data class User(
    val name: String,
    val imageId: Int,
    val role: String,
    val number: Int,
    val phoneNumber: String,
    val citizenship: String,
    val carType: String,
    val carNumber: String
)