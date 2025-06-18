package com.example.snakegame.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.snakegame.R
import com.example.snakegame.ui.theme.BackgroundColor
import com.example.snakegame.ui.theme.ButtonGreenColor

@Composable
fun MenuScreen(
    onPlayClick: () -> Unit,
    onLeaderboardClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val backgroundColor = BackgroundColor

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_snake),
            contentDescription = stringResource(id = R.string.snake_icon_string),
            modifier = Modifier
                .size(360.dp)
        )

        Spacer(modifier = Modifier.height(54.dp))

        MenuButton(
            text = stringResource(id = R.string.play_button_string),
            backgroundColor = ButtonGreenColor,
            onClick = onPlayClick
        )

        Spacer(modifier = Modifier.height(30.dp))

        MenuButton(
            text = stringResource(id = R.string.leaderboard_button_string),
            backgroundColor = ButtonGreenColor,
            onClick = onLeaderboardClick
        )

        Spacer(modifier = Modifier.height(30.dp))

        MenuButton(
            text = stringResource(id = R.string.profile_button_string),
            backgroundColor = ButtonGreenColor,
            onClick = onProfileClick
        )
    }
}