package com.example.snakegame

import User
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun LeaderboardScreen(
    topUsers: List<User>,
    onBackToWelcome: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Лидерборд", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))

            topUsers.forEachIndexed { index, user ->
                Text("${index + 1}. ${user.name} — ${user.highScore}")
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onBackToWelcome) {
                Text("Назад")
            }
        }
    }
}

