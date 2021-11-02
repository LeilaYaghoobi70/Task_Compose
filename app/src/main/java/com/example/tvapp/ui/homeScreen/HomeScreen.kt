package com.example.tvapp.ui.homeScreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tvapp.data.model.Task
import com.example.tvapp.utils.Destination
import com.example.tvapp.utils.EMPTY_SCREEN
import com.example.tvapp.utils.GetResult


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier,
    navController: NavHostController,

    ) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopAppBar(title = { Text(text = "Task") })
        Tasks(isLoading = false, tasks = state.getTasks)
        FloatingActionButton(
            onClick = {
                navController.navigate(Destination.NEW_TASK_SCREEN)
            },
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomEnd),
            backgroundColor = Blue,
            contentColor = White
        ) {
            Icon(Icons.Filled.Add, EMPTY_SCREEN)
        }
    }

}

@Composable
private fun Tasks(
    isLoading: Boolean,
    tasks: GetResult<List<Task>>?,
) {
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(10000) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        tasks?.let { getResultTask ->
            when (getResultTask) {
                is GetResult.Success -> getResultTask.data?.map { task -> ItemTask(task = task) }
                is GetResult.Error -> { }
                else -> Unit
            }
        }

        if (isLoading) {
            //Todo
        }
    }
}

@Composable
private fun ItemTask(task: Task) {
    Text(text = task.title)
}
