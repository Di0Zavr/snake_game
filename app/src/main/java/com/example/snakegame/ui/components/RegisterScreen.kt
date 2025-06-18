package com.example.snakegame.ui.components

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.snakegame.R
import com.example.snakegame.auth.AuthState
import com.example.snakegame.auth.AuthViewModel
import com.example.snakegame.auth.AuthViewModelFactory
import com.example.snakegame.ui.theme.BackgroundColor
import com.example.snakegame.ui.theme.ButtonGreenColor
import com.example.snakegame.ui.theme.ClickableTextColor
import com.example.snakegame.ui.theme.LoginInfoDataTextStyle
import com.example.snakegame.ui.theme.LoginTextBoxBackgroundColor
import kotlinx.coroutines.delay

@Composable
fun RegistrationScreen(
    onLoginOfferClick: () -> Unit
) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(context.applicationContext as Application)
    )
    val authState by authViewModel.authState.collectAsState()

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                message = (authState as AuthState.Success).message
                delay(1000)
                onLoginOfferClick()
            }
            is AuthState.Error -> {
                message = (authState as AuthState.Error).error
            }
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_snake),
            contentDescription = stringResource(id = R.string.snake_icon_string),
            modifier = Modifier
                .size(360.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .width(360.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(LoginTextBoxBackgroundColor)
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserTextField(
                value = username,
                onValueChange = { username = it },
                hint = stringResource(id = R.string.auth_username_hint),
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(12.dp))

            UserTextField(
                value = email,
                onValueChange = { email = it },
                hint = stringResource(id = R.string.auth_email_hint),
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(12.dp))

            UserTextField(
                value = password,
                onValueChange = { password = it },
                hint = stringResource(id = R.string.auth_password_hint),
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            UserTextField(
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                hint = stringResource(id = R.string.auth_password_confirm_hint),
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            MenuButton(
                text = stringResource(id = R.string.enter_button_string),
                backgroundColor = ButtonGreenColor,
                onClick = {
                    if (password == passwordConfirm) {
                        authViewModel.register(username, email, password)
                    } else {
                        message = "Пароли не совпадают"
                    }
                }
            )

            if (message.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message,
                    color = if (authState is AuthState.Error) Color.Red else Color.Green
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.login_offer_string),
                modifier = Modifier
                    .clickable { onLoginOfferClick() }
                    .padding(bottom = 12.dp),
                color = ClickableTextColor,
                style = LoginInfoDataTextStyle
            )
        }
    }
}