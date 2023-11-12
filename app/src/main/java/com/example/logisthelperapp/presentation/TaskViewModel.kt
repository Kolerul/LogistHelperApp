package com.example.logisthelperapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logisthelperapp.domain.model.Task
import com.example.logisthelperapp.domain.repository.TaskRepository
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    val repository: TaskRepository
): ViewModel() {

    private val _newTaskList = MutableLiveData(repository.getNewTasks())
    val newTaskList: LiveData<List<Task>>
        get() = _newTaskList

    private val _oldTaskList = MutableLiveData(repository.getOldTasks())
    val oldTaskList: LiveData<List<Task>>
        get() = _oldTaskList

    fun getTaskByNumber(number: Int): Task = repository.getTaskByNumber(number)
}