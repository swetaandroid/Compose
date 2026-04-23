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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.R

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.PrimaryGold,
    secondary = AppColors.SecondaryGold,
    tertiary = AppColors.CardBackground
)

private val LightColorScheme = lightColorScheme(
    primary = AppColors.PrimaryGold,
    secondary = AppColors.SecondaryGold,
    tertiary = AppColors.CardBackground

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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

// Font
val MatterRegular = FontFamily(Font(R.font.matter_regular, FontWeight.Normal))

val MatterMedium = FontFamily(Font(R.font.matter_medium, FontWeight.Medium))

val MatterBold = FontFamily(Font(R.font.matter_bold, FontWeight.Bold))

val MatterSemiBold = FontFamily(Font(R.font.matter_semibold, FontWeight.SemiBold))

// Typography (maps your XML text styles)
val AppTypography = androidx.compose.material3.Typography(
    bodyLarge = TextStyle(
        fontFamily = MatterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),

    titleLarge = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),

    labelLarge = TextStyle(
        fontFamily = MatterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    ),

    displayLarge = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold
    ),
    displaySmall = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium
    )
)