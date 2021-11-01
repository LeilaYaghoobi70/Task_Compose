package com.example.tvapp.data.repository

import com.example.tvapp.data.db.TaskDao
import com.example.tvapp.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getTasks(): Flow<List<Task>> = taskDao.getTask()

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
}