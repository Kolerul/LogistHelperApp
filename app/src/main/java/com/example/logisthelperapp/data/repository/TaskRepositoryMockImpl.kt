package com.example.logisthelperapp.data.repository

import com.example.logisthelperapp.domain.model.Contact
import com.example.logisthelperapp.domain.model.MapPoint
import com.example.logisthelperapp.domain.model.MapPointType
import com.example.logisthelperapp.domain.model.Task
import com.example.logisthelperapp.domain.model.TaskInfo
import com.example.logisthelperapp.domain.model.TaskStatus
import com.example.logisthelperapp.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryMockImpl @Inject constructor(): TaskRepository {
    override fun getAllTasks(): List<Task> = tasks

    override fun getNewTasks(): List<Task> = tasks.filter {task ->
            task.taskStatus == TaskStatus.NEW
        }


    override fun getOldTasks(): List<Task> = tasks.filter { task ->
        task.taskStatus != TaskStatus.NEW
    }

    override fun getTaskByNumber(number: Int): Task {
        if (number <= 1) return task1
        if (number <= 2) return task2
        if (number <= 3) return task3
        else return task4
    }

    override fun changeTaskStatus(task: Task, status: TaskStatus) {

    }
}

val contact1 = Contact("Вован", "+7 800 000 00 00")
val contact2 = Contact("Антоха", "+7 800 000 00 00")
val taskInfo = TaskInfo(
    "Тяжелый груз", "Москва", "01.02.2003", "Грузовик",
    2000, 10_000, "Задняя погрузка", true, "Привезите пожалуйста",
    listOf(contact1, contact2)
)

val route2 = listOf(
    MapPoint("Совсем близкая улица, 45", MapPointType.LOADING, "01.02.2003 12:00", "ООО Зашибись", contact1, "Ноу коммент"),
    MapPoint("Очень далекий проспект, 51", MapPointType.UNLOADING, "01.02.2003 12:01", "ООО Почешись", contact2, "Ноу коммент")
)

val route3 = listOf(
    MapPoint("Проспект победителей, 19", MapPointType.LOADING, "01.02.2003 12:00", "ООО Зашибись", contact1, "Ноу коммент"),
    MapPoint("Улица ничейных, 17", MapPointType.LOADING, "01.02.2003 12:01", "ООО Ты как тут оказался?", contact2, "Ноу коммент"),
    MapPoint("Бульвар проигравших, 37", MapPointType.UNLOADING, "01.02.2003 12:02", "ООО Почешись", contact2, "Ноу коммент")
)

val task1 = Task(
    1, 100_000, TaskStatus.NEW, taskInfo, route2
)

val task2 = Task(
    2, 150_000, TaskStatus.IN_PROGRESS, taskInfo, route3
)

val task3 = Task(
    3, 100_900, TaskStatus.PLANNED, taskInfo, route2
)

val task4 = Task(
    4, 10_000, TaskStatus.CHECK, taskInfo, route3
)

val tasks = listOf(task1, task2, task3, task4, task1, task2, task3, task4, task1, task2, task3, task4, task1, task2, task3, task4)