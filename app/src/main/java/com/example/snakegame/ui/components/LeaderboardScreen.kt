package com.example.snakegame.ui.components

import User
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snakegame.R
import com.example.snakegame.ui.theme.BackgroundColor
import com.example.snakegame.ui.theme.ButtonGreenColor
import com.example.snakegame.ui.theme.ButtonGreyColor
import com.example.snakegame.ui.theme.ButtonLightBlueColor

@Composable
fun LeaderboardScreen(
    topUsers: List<User>,
    onPlayClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        MenuTextBox(
            text = stringResource(id = R.string.leaderboard_button_string),
            backgroundColor = ButtonLightBlueColor
        )

        Spacer(modifier = Modifier.height(54.dp))

        topUsers.forEachIndexed { index, user ->
            if (index != 0) Spacer(modifier = Modifier.height(12.dp))
            MenuTextBox(
                text = "${user.name} — ${user.highScore}",
                backgroundColor = ButtonGreyColor
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MenuButton(
            text = stringResource(id = R.string.play_button_string),
            backgroundColor = ButtonGreenColor,
            onClick = onPlayClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        MenuButton(
            text = stringResource(id = R.string.back_to_menu_button_string),
            backgroundColor = ButtonGreyColor,
            onClick = onBackClick
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}
