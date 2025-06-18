package com.example.snakegame.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import AppDatabase
import com.example.snakegame.model.GameResult
import kotlinx.coroutines.launch

class GameResultViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val gameResultDao = db.gameResultDao()
    private val userDao = db.userDao()

    fun saveResult(userId: Int, score: Int) {
        viewModelScope.launch {
            gameResultDao.insertGameResult(GameResult(userId = userId, score = score))

            val highScore = gameResultDao.getHighScore(userId)
            if (highScore != null) {
                userDao.updateHighScore(userId, highScore)
            }
        }
    }
}
