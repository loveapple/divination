// 梅花易数 Android 应用 - Version 1.0 (MVP)
// Copyright © 2025 番创知库 (FanChuang Knowledge Base)
// Package: cn.loveapple.divination
// License: Proprietary - All rights reserved

package cn.loveapple.divination.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6B5B95),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF4A3F6B),
    onPrimaryContainer = Color(0xFFFFE0E0),
    secondary = Color(0xFFF4A460),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD2691E),
    onSecondaryContainer = Color(0xFFFFE0E0),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFED8E3),
    onTertiaryContainer = Color(0xFF31101D),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1F1B1F),
    onBackground = Color(0xFFE8DEE0),
    surface = Color(0xFF1F1B1F),
    onSurface = Color(0xFFE8DEE0),
    surfaceVariant = Color(0xFF49454E),
    onSurfaceVariant = Color(0xFFCAC7D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454E),
    scrim = Color.Black,
    inverseSurface = Color(0xFFE8DEE0),
    inverseOnSurface = Color(0xFF1F1B1F),
    inversePrimary = Color(0xFF6B5B95),
    surfaceDim = Color(0xFF1F1B1F),
    surfaceBright = Color(0xFF49454E),
    surfaceContainerLowest = Color(0xFF1A1618),
    surfaceContainerLow = Color(0xFF28242A),
    surfaceContainer = Color(0xFF2C282E),
    surfaceContainerHigh = Color(0xFF373239),
    surfaceContainerHighest = Color(0xFF423D43)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B5B95),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF0E0FF),
    onPrimaryContainer = Color(0xFF22005E),
    secondary = Color(0xFFF4A460),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDCC4),
    onSecondaryContainer = Color(0xFF3F2817),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFED8E3),
    onTertiaryContainer = Color(0xFF31101D),
    error = Color(0xFFB3261E),
    onError = Color.White,
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),
    background = Color(0xFFFAF8F3),
    onBackground = Color(0xFF1F1B1F),
    surface = Color(0xFFFAF8F3),
    onSurface = Color(0xFF1F1B1F),
    surfaceVariant = Color(0xFFE8DEE0),
    onSurfaceVariant = Color(0xFF49454E),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC7D0),
    scrim = Color.Black,
    inverseSurface = Color(0xFF1F1B1F),
    inverseOnSurface = Color(0xFFF5EFF7),
    inversePrimary = Color(0xFFD0BCFF),
    surfaceDim = Color(0xFFDED8DF),
    surfaceBright = Color(0xFFFAF8F3),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF4EFF7),
    surfaceContainer = Color(0xFFEEE9F1),
    surfaceContainerHigh = Color(0xFFE8DEE0),
    surfaceContainerHighest = Color(0xFFE2D8DB)
)

@Composable
fun MeihuaYishuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

