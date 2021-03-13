package com.primalabs.primatodo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Yellow,
    primaryVariant = DarkBlue,
    secondary = BrightPurple
)

private val LightColorPalette = lightColors(
    primary = Blue,
    onPrimary = FaintBlue,
    primaryVariant = DarkBlue,
    secondary = BrightPurple,
    onSecondary = White,
    surface = DarkBlue,
    onSurface = White,
    background = Blue,
    onBackground = White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PrimaTodoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}