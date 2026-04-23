package com.example.compose.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Text16SemiBoldBlack(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun Text14SemiBoldBlack(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
fun Text12SemiBoldBlack(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall
    )
}