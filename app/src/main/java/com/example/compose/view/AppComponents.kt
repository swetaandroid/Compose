package com.example.compose.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.R

val AppGold = Color(0xFFAD8B3A)

@Composable
fun AppBottomNavigation(currentScreen: String) {
    val context = LocalContext.current
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(100.dp)
    ) {
        // Background bar
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .align(Alignment.BottomCenter),
            color = Color(0xFF1A1A1A),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(
                    iconId = R.drawable.ic_home_yellow,
                    isSelected = currentScreen == "home",
                    onClick = {
                        if (currentScreen != "home") {
                            navigateToActivity(context, HomeActivity::class.java)
                        }
                    }
                )
                BottomNavItem(
                    iconId = R.drawable.ic_calander_gray,
                    isSelected = currentScreen == "calendar",
                    onClick = {
                        if (currentScreen != "calendar") {
                            navigateToActivity(context, CalendarActivity::class.java)
                        }
                    }
                )
                
                Spacer(modifier = Modifier.width(60.dp)) // FAB space
                
                BottomNavItem(
                    iconId = R.drawable.ic_notification_gray,
                    isSelected = currentScreen == "notifications",
                    onClick = {
                        if (currentScreen != "notifications") {
                            navigateToActivity(context, NotificationActivity::class.java)
                        }
                    }
                )
                BottomNavItem(
                    iconId = R.drawable.ic_account_gray,
                    isSelected = currentScreen == "profile",
                    onClick = {
                        if (currentScreen != "profile") {
                            navigateToActivity(context, ProfileActivity::class.java)
                        }
                    }
                )
            }
        }

        // 🔹 Center AI Button (Opens Home Activity)
        Surface(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopCenter)
                .offset(y = 4.dp)
                .clickable { 
                    if (currentScreen != "home") {
                        navigateToActivity(context, HomeActivity::class.java)
                    }
                },
            shape = CircleShape,
            color = AppGold,
            shadowElevation = 8.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_center_logo),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(iconId: Int, isSelected: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if (isSelected) AppGold else Color.Gray,
            modifier = Modifier.size(28.dp)
        )
    }
}

private fun navigateToActivity(context: Context, destination: Class<*>) {
    if (context::class.java == destination) return
    
    val intent = Intent(context, destination)
    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or 
                   Intent.FLAG_ACTIVITY_SINGLE_TOP or 
                   Intent.FLAG_ACTIVITY_NO_ANIMATION
    
    context.startActivity(intent)
    if (context is Activity) {
        context.overridePendingTransition(0, 0)
    }
}
