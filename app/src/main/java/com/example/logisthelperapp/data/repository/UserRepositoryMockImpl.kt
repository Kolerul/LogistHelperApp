package com.example.logisthelperapp.data.repository

import com.example.logisthelperapp.R
import com.example.logisthelperapp.domain.model.AutorizationData
import com.example.logisthelperapp.domain.model.User
import com.example.logisthelperapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryMockImpl @Inject constructor(): UserRepository {
    override fun logIn(data: AutorizationData): User =
        User(
            name = "Добрыня Никитич",
            role = "Богатырь",
            imageId = R.drawable.dobrynya,
            number = 1,
            phoneNumber = data.phoneNumber,
            citizenship = "Русь-матушка",
            carType = "Гужевой",
            carNumber = "1111"
        )
}
