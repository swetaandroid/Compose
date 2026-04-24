package com.example.compose.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.compose.ui.theme.AppColors.AppBgLight
import com.example.compose.ui.theme.AppColors.AppGold
import com.example.compose.ui.theme.AppColors.AppGoldDark

private val DarkColorScheme = darkColorScheme(
    primary = AppGold,
    secondary = AppGoldDark,
    tertiary = AppBgLight
)

private val LightColorScheme = lightColorScheme(
    primary = AppGold,
    secondary = AppGoldDark,
    tertiary = AppBgLight
)

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
