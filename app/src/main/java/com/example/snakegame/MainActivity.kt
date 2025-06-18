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
import com.example.snakegame.ui.components.LeaderboardScreen
import com.example.snakegame.ui.components.LoginScreen
import com.example.snakegame.ui.components.MenuScreen
import com.example.snakegame.ui.components.ProfileScreen
import com.example.snakegame.ui.components.RegisterScreen
import com.example.snakegame.ui.components.SnakeGameScreen

import kotlin.Int

class MainActivity : ComponentActivity() {

    enum class Screen {
        REGISTER, LOGIN, MENU, GAME, PROFILE, LEADERBOARD
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SnakeGameTheme {
                var currentScreen by remember { mutableStateOf(Screen.LOGIN) }

                val authViewModel: AuthViewModel = viewModel(
                    factory = AuthViewModelFactory(application)
                )

                when (currentScreen) {
                    Screen.REGISTER -> RegisterScreen (
                        onLoginOfferClick = { currentScreen = Screen.LOGIN }
                    )

                    Screen.LOGIN -> LoginScreen (
                        viewModel = authViewModel,
                        onLoginSuccess = { currentScreen = Screen.MENU },
                        onForgotPasswordClick = {},
                        onRegisterClick = { currentScreen = Screen.REGISTER }
                    )

                    Screen.MENU -> MenuScreen (
                        onPlayClick = { currentScreen = Screen.GAME },
                        onProfileClick = { currentScreen = Screen.PROFILE },
                        onLeaderboardClick = { currentScreen = Screen.LEADERBOARD }
                    )

                    Screen.GAME -> {
                        val viewModel = viewModel<SnakeGameViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        val user by authViewModel.currentUser.collectAsStateWithLifecycle()

                        if (user != null) {
                            SnakeGameScreen (
                                state = state,
                                onEvent = viewModel::onEvent,
                                userId = user!!.id,
                                onNavigateToWelcome = { currentScreen = Screen.MENU }
                            )
                        }
                        else {
                            LoginScreen (
                                viewModel = authViewModel,
                                onLoginSuccess = { currentScreen = Screen.MENU },
                                onForgotPasswordClick = {},
                                onRegisterClick = { currentScreen = Screen.REGISTER }
                            )
                        }
                    }

                    Screen.PROFILE -> {
                        val user by authViewModel.currentUser.collectAsStateWithLifecycle()
                        user?.let {
                            ProfileScreen(
                                user = it,
                                onChangePasswordClick = {},
                                onBackToMenuClick = { currentScreen = Screen.MENU },
                                onLogoutClick = {}
                            )
                        }
                    }

                    Screen.LEADERBOARD -> {
                        val topUsers by authViewModel.topUsers.collectAsStateWithLifecycle(emptyList())

                        LeaderboardScreen (
                            topUsers = topUsers,
                            onPlayClick = { currentScreen = Screen.GAME },
                            onBackClick = { currentScreen = Screen.MENU }
                        )
                    }

                }
            }
        }
    }
}
