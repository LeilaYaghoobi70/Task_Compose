package com.example.tvapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tvapp.ui.theme.TvAppTheme
import com.example.tvapp.utils.SettingNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modifier = Modifier.background(color = Color.White)
        setContent {
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