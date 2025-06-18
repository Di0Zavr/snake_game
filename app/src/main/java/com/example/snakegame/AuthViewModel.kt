package com.example.snakegame.auth

import User
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val error: String) : AuthState()
}

class AuthViewModel(application: Application) : AndroidViewModel(application) {


    private val userDao = AppDatabase.getDatabase(application).userDao()
    val topUsers: Flow<List<User>> = userDao.getTopUsers()
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val existingUser = userDao.getUserByEmail(email)
            if (existingUser != null) {
                _authState.value = AuthState.Error("Пользователь с таким email уже существует")
                return@launch
            }

            val user = User(name = name, email = email, password = password, highScore = 0)
            userDao.insertUser(user)
            _authState.value = AuthState.Success("Регистрация успешна!")
        }
    }

    companion object {
        var currentUserId: Int? = null
        var currentUserEmail: String? = null
    }

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val user = userDao.authenticate(email, password)
            if (user != null) {
                _currentUser.value = user
                _authState.value = AuthState.Success("Вход выполнен")
            } else {
                _authState.value = AuthState.Error("Неверный email или пароль")
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}
