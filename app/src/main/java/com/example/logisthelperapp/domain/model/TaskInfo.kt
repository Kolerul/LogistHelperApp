package com.example.logisthelperapp.domain.model

data class TaskInfo(
    val typeOfCargo: String,
    val city: String,
    val orderDate: String,
    val bodyType: String,
    val cargoWeight: Int,
    val loadCapacity: Int,
    val typeOfLoading: String,
    val medicalBook: Boolean,
    val orderDetails: String,
    val contacts: List<Contact>
)