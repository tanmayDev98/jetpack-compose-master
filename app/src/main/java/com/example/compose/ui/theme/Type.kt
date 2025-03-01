package com.example.compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
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

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC), // Darker variant of primary color
    secondary = Color(0xFF03DAC6),  // Secondary color (teal)
    background = Color(0xFF121212), // Background color (dark)
    surface = Color(0xFF121212),    // Surface color (dark)
    error = Color(0xFFCF6679),       // Error color (light red)
    onPrimary = Color.Black,         // Text color on primary background
    onSecondary = Color.Black,       // Text color on secondary background
    onBackground = Color.White,      // Text color on background
    onSurface = Color.White,         // Text color on surface
    onError = Color.Black            // Text color on error
)

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),   // Primary color
    secondary = Color(0xFF03DAC6), // Secondary color
    background = Color(0xFFFFFFFF), // Background color (light)
    surface = Color(0xFFFFFFFF),    // Surface color (light)
    error = Color(0xFFB00020),      // Error color
    onPrimary = Color.White,        // Text color on primary background
    onSecondary = Color.Black,      // Text color on secondary background
    onBackground = Color.Black,     // Text color on background
    onSurface = Color.Black,        // Text color on surface
    onError = Color.White           // Text color on error
)
