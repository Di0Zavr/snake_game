package com.example.snakegame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.snakegame.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val ButtonTextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_extra_bold)),
    fontSize = 22.sp,
    color = ButtonTextColor,
    textAlign = TextAlign.Center
)

val LoginUserDataTextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
    fontSize = 16.sp,
    color = LoginUserDataTextColor
)

val LoginInfoDataTextStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
    fontSize = 14.sp,
    color = LoginInfoDataTextColor
)