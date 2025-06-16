package com.example.snakegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.snakegame.ui.theme.SnakeGameTheme

class MainActivity : ComponentActivity() {

    enum class Screen {
        REGISTER, LOGIN, WELCOME, GAME, PROFILE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeGameTheme {
                var currentScreen by remember { mutableStateOf(Screen.REGISTER) }

                when (currentScreen) {
                    Screen.REGISTER -> RegisterScreen(
                        onRegisterClick = { currentScreen = Screen.LOGIN }
                    )
                    Screen.LOGIN -> LoginScreen(
                        onLoginClick = { currentScreen = Screen.WELCOME }
                    )
                    Screen.WELCOME -> WelcomeScreen(
                        onStartClick = { currentScreen = Screen.GAME },
                        onProfileClick = { currentScreen = Screen.PROFILE }
                    )
                    Screen.GAME -> {
                        val viewModel = viewModel<SnakeGameViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        SnakeGameScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    Screen.PROFILE -> ProfileScreen(
                        onBackToWelcome = { currentScreen = Screen.WELCOME }
                    )
                }
            }
        }
    }
}
