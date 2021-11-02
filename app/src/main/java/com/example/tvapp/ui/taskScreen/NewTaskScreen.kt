package com.example.tvapp.ui.taskScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.tvapp.data.model.Task


@Composable
fun NewTaskScreen(
    newTaskViewModel: NewTaskViewModel,
    navController: NavController,
    modifier: Modifier
) {
    Column(modifier = modifier) {
       Button(onClick = {
           newTaskViewModel.addNewTask(task = Task(id = 0,
               title = "Ali",
               description = "Leila",
               time = "20"))
       }) {

       }

    }
}