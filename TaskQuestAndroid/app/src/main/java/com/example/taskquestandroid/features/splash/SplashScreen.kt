// SplashScreen.kt
package com.tudominio.taskquest.ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    var showLogo by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }
    var showIcons by remember { mutableStateOf(false) }

    // Animaciones
    val logoScale by animateFloatAsState(
        targetValue = if (showLogo) 1f else 0.5f,
        animationSpec = tween(1000)
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (showText) 1f else 0f,
        animationSpec = tween(1000)
    )

    LaunchedEffect(Unit) {
        showLogo = true
        delay(500)
        showText = true
        delay(500)
        showIcons = true
        delay(1500)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E1B4B), // purple-900
                        Color(0xFF1E3A8A), // blue-900
                        Color(0xFF312E81)  // indigo-900
                    )
                )
            )
    ) {
        // Logo central
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(128.dp)
                .scale(logoScale)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF6366F1), // indigo-500
                            Color(0xFF9333EA)  // purple-600
                        )
                    )
                )
        ) {
            // Aquí irá el ícono del Trophy
        }

        // Texto
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 100.dp)
                .alpha(textAlpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TaskQuest",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tu aventura hacia la productividad",
                fontSize = 16.sp,
                color = Color(0xFF93C5FD) // blue-200
            )
        }
    }
}