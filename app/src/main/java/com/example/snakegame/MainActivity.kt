package com.example.snakegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.snakegame.auth.AuthViewModel
import com.example.snakegame.auth.AuthViewModelFactory
import com.example.snakegame.ui.theme.SnakeGameTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlin.Int

class MainActivity : ComponentActivity() {

    enum class Screen {
        REGISTER, LOGIN, WELCOME, GAME, PROFILE, LEADERBOARD
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SnakeGameTheme {
                var currentScreen by remember { mutableStateOf(Screen.REGISTER) }

                // 👇 Инициализируем AuthViewModel с factory
                val authViewModel: AuthViewModel = viewModel(
                    factory = AuthViewModelFactory(application)
                )

                when (currentScreen) {
                    Screen.REGISTER -> RegisterScreen(
                        onRegisterClick = { currentScreen = Screen.LOGIN }
                    )

                    Screen.LOGIN -> LoginScreen(
                        viewModel = authViewModel,
                        onLoginSuccess = { currentScreen = Screen.WELCOME }
                    )

                    Screen.WELCOME -> WelcomeScreen(
                        onStartClick = { currentScreen = Screen.GAME },
                        onProfileClick = { currentScreen = Screen.PROFILE },
                        onLeaderboardClick = { currentScreen = Screen.LEADERBOARD }
                    )

                    Screen.GAME -> {
                        val viewModel = viewModel<SnakeGameViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        val user by authViewModel.currentUser.collectAsStateWithLifecycle()

                        if (user != null) {
                            SnakeGameScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                userId = user!!.id,
                                onNavigateToWelcome = { currentScreen = Screen.WELCOME } // ✅ передано
                            )

                        }
                    }



                    Screen.PROFILE -> {
                        val user by authViewModel.currentUser.collectAsStateWithLifecycle()
                        user?.let {
                            ProfileScreen(
                                user = it,
                                onBackToWelcome = { currentScreen = Screen.WELCOME }
                            )
                        }
                    }


                    Screen.LEADERBOARD -> {
                        val topUsers by authViewModel.topUsers.collectAsStateWithLifecycle(emptyList())

                        LeaderboardScreen(
                            topUsers = topUsers,
                            onBackToWelcome = { currentScreen = Screen.WELCOME }
                        )
                    }

                }
            }
        }
    }
}
