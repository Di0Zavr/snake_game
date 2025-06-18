package com.example.snakegame.data

import androidx.room.*
import com.example.snakegame.model.GameResult

@Dao
interface GameResultDao {
    @Insert
    suspend fun insertGameResult(result: GameResult)

    @Query("SELECT MAX(score) FROM GameResult WHERE userId = :userId")
    suspend fun getHighScore(userId: Int): Int?
}
