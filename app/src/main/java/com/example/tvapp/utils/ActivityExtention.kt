package com.example.tvapp.utils

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tvapp.ui.homeScreen.HomeScreen
import com.example.tvapp.ui.taskScreen.NewTaskScreen

object Destination{
   const val HOME_SCREEN = "HomeScreen"
    const val NEW_TASK_SCREEN = "NewTaskScreen"
}
@Composable
fun Activity.SettingNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destination.HOME_SCREEN){
            HomeScreen(hiltViewModel(),navController = navController, modifier = modifier)
        }
        composable(Destination.NEW_TASK_SCREEN){
            NewTaskScreen(hiltViewModel(),navController = navController,modifier = modifier)
        }
    }
}