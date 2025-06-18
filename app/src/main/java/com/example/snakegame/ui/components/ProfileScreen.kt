package com.example.snakegame.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.snakegame.R
import com.example.snakegame.ui.theme.BackgroundColor
import com.example.snakegame.ui.theme.ButtonGreyColor
import com.example.snakegame.ui.theme.ButtonRedColor

@Composable
fun ProfileScreen(
    onChangePasswordClick: () -> Unit,
    onBackToMenuClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_snake),
            contentDescription = stringResource(id = R.string.snake_icon_string),
            modifier = Modifier.size(360.dp)
        )

        Spacer(modifier = Modifier.height(54.dp))

        MenuButton(
            text = stringResource(id = R.string.change_password_button_string),
            backgroundColor = ButtonGreyColor,
            onClick = onChangePasswordClick
        )

        Spacer(modifier = Modifier.weight(1f)) // Push buttons to bottom

        MenuButton(
            text = stringResource(id = R.string.back_to_menu_button_string),
            backgroundColor = ButtonGreyColor,
            onClick = onBackToMenuClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        MenuButton(
            text = stringResource(id = R.string.log_out_button_string),
            backgroundColor = ButtonRedColor, // как в shape_rounded_red_button
            onClick = onLogoutClick
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}
