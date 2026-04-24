package com.example.compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.R

// Font Families
val MatterRegular = FontFamily(Font(R.font.matter_regular, FontWeight.Normal))
val MatterMedium = FontFamily(Font(R.font.matter_medium, FontWeight.Medium))
val MatterBold = FontFamily(Font(R.font.matter_bold, FontWeight.Bold))
val MatterSemiBold = FontFamily(Font(R.font.matter_semibold, FontWeight.SemiBold))

// Define custom text styles
val AppTypography = Typography(
    // Large Title (e.g., Home Header)
    displayLarge = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = Color.White
    ),
    // Section Header (e.g., Categories, Top Venues)
    titleLarge = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.Black
    ),
    // Banner Title
    titleMedium = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.White
    ),
    // Item Title (e.g., Venue Name)
    titleSmall = TextStyle(
        fontFamily = MatterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = Color.Black
    ),
    // Body Text Large
    bodyLarge = TextStyle(
        fontFamily = MatterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black
    ),
    // Body Text Medium (e.g., Category names)
    bodyMedium = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        color = Color.Black
    ),
    // Body Text Small (e.g., See more, Location)
    bodySmall = TextStyle(
        fontFamily = MatterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.Gray
    ),
    // Extra Small (e.g., Location text in header)
    labelSmall = TextStyle(
        fontFamily = MatterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = Color.Gray
    ),
    // Notification text etc
    labelMedium = TextStyle(
        fontFamily = MatterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.Black
    )
)
