package com.example.snakegame.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.snakegame.ui.theme.ButtonTextStyle

@Composable
fun MenuTextBox(
    text: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = ButtonTextStyle,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

