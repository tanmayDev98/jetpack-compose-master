package com.example.compose.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(text: String, show: Boolean, onToastComplete: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(show) {
        if (show) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
            onToastComplete()
        }
    }
}