package com.example.snakegame.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snakegame.R
import com.example.snakegame.auth.AuthState
import com.example.snakegame.auth.AuthViewModel
import com.example.snakegame.ui.theme.BackgroundColor
import com.example.snakegame.ui.theme.ButtonGreenColor
import com.example.snakegame.ui.theme.ClickableTextColor
import com.example.snakegame.ui.theme.LoginInfoDataTextStyle
import com.example.snakegame.ui.theme.LoginTextBoxBackgroundColor

//@Composable
//fun LoginScreen(
//    viewModel: AuthViewModel,
//    onLoginSuccess: () -> Unit
//) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    val authState by viewModel.authState.collectAsState()
//
//    LaunchedEffect(authState) {
//        if (authState is AuthState.Success) {
//            onLoginSuccess()
//            viewModel.resetState()
//        }
//    }
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(24.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Вход", fontSize = 32.sp)
//            Spacer(modifier = Modifier.height(32.dp))
//
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Пароль") },
//                visualTransformation = PasswordVisualTransformation(),
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = { viewModel.login(email, password) },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Войти")
//            }
//
//            if (authState is AuthState.Error) {
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = (authState as AuthState.Error).error,
//                    color = MaterialTheme.colorScheme.error
//                )
//            }
//        }
//    }
//}

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val backgroundColor = BackgroundColor
    val boxShape = RoundedCornerShape(16.dp)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onLoginSuccess()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp),
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
                .clip(boxShape)
                .background(LoginTextBoxBackgroundColor) // shape_login_box background
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            UserTextField(
                value = password,
                onValueChange = { password = it },
                hint = stringResource(R.string.auth_email_hint),
                keyboardType = KeyboardType.Email,
            )

            Spacer(modifier = Modifier.height(12.dp))

            UserTextField(
                value = email,
                onValueChange = { email = it },
                hint = stringResource(R.string.auth_password_hint),
                keyboardType = KeyboardType.Password,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Forgot password text
            Text(
                text = stringResource(id = R.string.forget_password_string),
                modifier = Modifier
                    .clickable { onForgotPasswordClick() }
                    .padding(bottom = 12.dp),
                color = ClickableTextColor,
                style = LoginInfoDataTextStyle
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Login Button
            MenuButton(
                text = stringResource(R.string.enter_button_string),
                backgroundColor = ButtonGreenColor,
                onClick = { viewModel.login(email, password) }
            )

            if (authState is AuthState.Error) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = (authState as AuthState.Error).error,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            // Register offer text
            Text(
                text = stringResource(id = R.string.register_offer_string),
                modifier = Modifier
                    .clickable { onRegisterClick() }
                    .padding(bottom = 12.dp),
                color = ClickableTextColor,
                style = LoginInfoDataTextStyle
            )
        }
    }
}