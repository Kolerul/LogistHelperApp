package com.example.logisthelperapp.domain.model

data class Task(
    val number: Int,
    val price: Int,
    val taskStatus: TaskStatus,
    val taskInfo: TaskInfo,
    val route: List<MapPoint>
)