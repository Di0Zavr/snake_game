package com.example.snakegame.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.snakegame.ui.theme.ButtonTextStyle

@Composable
fun MenuButton(
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button (
        onClick = onClick,
        modifier = Modifier
            .width(336.dp)
            .height(60.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues()
    ) {
        Text(
            text = text,
            style = ButtonTextStyle,
            modifier = Modifier.fillMaxWidth()
        )
    }
}