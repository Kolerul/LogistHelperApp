package com.example.logisthelperapp.domain.repository

import com.example.logisthelperapp.domain.model.Task
import com.example.logisthelperapp.domain.model.TaskStatus

interface TaskRepository {

    fun getAllTasks(): List<Task>

    fun getNewTasks(): List<Task>

    fun getOldTasks(): List<Task>

    fun getTaskByNumber(number: Int): Task

    fun changeTaskStatus(task: Task, status: TaskStatus)
}