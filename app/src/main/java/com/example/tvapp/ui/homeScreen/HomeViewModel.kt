package com.example.tvapp.ui.homeScreen

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvapp.base.BaseViewModel
import com.example.tvapp.data.model.Task
import com.example.tvapp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mTaskRepository: TaskRepository
) : BaseViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state

    init {
        getTask()
    }

    private fun getTask() {
        viewModelScope.launch(Dispatchers.IO) {
            mTaskRepository.getTasks().catch {
                _state.value = HomeState(getTasks = Result.failure(it))
            }.collect {
                _state.value = HomeState(getTasks = Result.success(it))
            }
        }
    }
}

data class HomeState(
    val getTasks: Result<List<Task>>? = null,
    val refreshing: Boolean = false
)


