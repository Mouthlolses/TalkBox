package com.mytalkbox.talkbox.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// 🌙 DARK MODE
private val DarkColorScheme = darkColorScheme(
    primary = Blue40,
    onPrimary = White80,
    background = Blue20,
    onBackground = White80,
    surface = Blue30,
    onSurface = White80,
    outline = AccentBlue,
    secondary = BlueGrey80,
    onSecondary = Blue20,
    tertiary = AccentBlue,
    onTertiary = Blue20
)

// ☀️ LIGHT MODE
private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = White80,
    primaryContainer = Blue80,
    onPrimaryContainer = Blue20,
    background = White80,
    onBackground = BlueGrey40,
    surface = White80,
    onSurface = BlueGrey40,
    outline = Blue30,
    secondary = BlueGrey80,
    onSecondary = Blue20,
    tertiary = AccentBlue,
    onTertiary = White80
)

@Composable
fun TalkBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}