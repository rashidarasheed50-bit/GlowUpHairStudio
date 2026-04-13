package com.glowup.hairstudio.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = PrimaryPink,
    onPrimary = White,
    primaryContainer = LightPink,
    onPrimaryContainer = TextPrimary,
    
    secondary = Gold,
    onSecondary = TextPrimary,
    secondaryContainer = LightGold,
    onSecondaryContainer = TextPrimary,
    
    tertiary = DarkPink,
    onTertiary = White,
    
    background = BackgroundLight,
    onBackground = TextPrimary,
    
    surface = SurfaceLight,
    onSurface = TextPrimary,
    
    surfaceVariant = LightGray,
    onSurfaceVariant = TextSecondary,
    
    error = Error,
    onError = White,
    
    outline = MediumGray,
    outlineVariant = LightGray
)

@Composable
fun GlowUpHairStudioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // For now, only light theme (salon app looks better in light theme)
    val colorScheme = LightColorScheme
    
    val systemUiController = rememberSystemUiController()
    
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            color = White,
            darkIcons = true
        )
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
