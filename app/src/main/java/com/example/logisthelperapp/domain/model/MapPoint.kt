package com.example.logisthelperapp.domain.model

data class MapPoint(
    val startPoint: String,
    val type: MapPointType,
    val dateAndTime: String,
    val company: String,
    val contact: Contact,
    val comment: String
)