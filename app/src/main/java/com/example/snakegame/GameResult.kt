package com.example.snakegame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val score: Int
)
