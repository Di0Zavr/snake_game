package com.example.snakegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.snakegame.ui.theme.SnakeGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeGameTheme {
                var isGameStarted by remember { mutableStateOf(false) }

                if (!isGameStarted) {
                    WelcomeScreen(
                        onStartClick = { isGameStarted = true }
                    )
                } else {
                    val viewModel = viewModel<SnakeGameViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    SnakeGameScreen(
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}


