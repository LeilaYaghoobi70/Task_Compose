package com.example.tvapp.ui.taskScreen

import androidx.lifecycle.viewModelScope
import com.example.tvapp.base.BaseViewModel
import com.example.tvapp.data.model.Task
import com.example.tvapp.data.repository.TaskRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTaskViewModel @Inject constructor(
    private val mTaskRepository: TaskRepository
): BaseViewModel() {

    fun addNewTask(task: Task) = viewModelScope.launch {
        mTaskRepository.insertTask(task = task)
    }

}
