package com.example.snakegame.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.snakegame.ui.theme.LoginTextBoxStrokeColor
import com.example.snakegame.ui.theme.LoginUserDataTextStyle

@Composable
fun UserTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    keyboardType: KeyboardType,
    isPassword: Boolean = false
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(hint) },
        singleLine = true,
        modifier = Modifier
            .width(336.dp)
            .height(48.dp),
//            .border(1.dp, LoginTextBoxStrokeColor, RoundedCornerShape(3.dp)),
        shape = RoundedCornerShape(3.dp),
        textStyle = LoginUserDataTextStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}