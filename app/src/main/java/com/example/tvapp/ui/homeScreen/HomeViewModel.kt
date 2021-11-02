package com.example.tvapp.ui.homeScreen

import androidx.lifecycle.viewModelScope
import com.example.tvapp.base.BaseViewModel
import com.example.tvapp.data.model.Task
import com.example.tvapp.data.repository.TaskRepository
import com.example.tvapp.utils.GetResult
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _state =  MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state

    init {
        getTask()
    }

    private fun getTask() {
        viewModelScope.launch {
            _state.value = HomeState(loading = true)
            mTaskRepository.getTasks().catch {
                _state.value = HomeState(getTasks = GetResult.Error(it),loading = false)
            }.collect {
                _state.value = HomeState(getTasks =GetResult.Success(it),loading = false)
            }
        }
    }
}

data class HomeState(
    val getTasks: GetResult<List<Task>>? = null,
    val loading: Boolean = false
)


