package com.example.tvapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.tvapp.ui.theme.TvAppTheme
import com.example.tvapp.utils.SettingNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val modifier = Modifier.background(color = colorResource(R.color.background))
            TvAppTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = modifier
                ) {
                    SettingNavHost(
                        navController = rememberNavController(),
                        startDestination = "HomeScreen",
                        modifier = modifier
                    )
                }
            }
        }
    }
}
