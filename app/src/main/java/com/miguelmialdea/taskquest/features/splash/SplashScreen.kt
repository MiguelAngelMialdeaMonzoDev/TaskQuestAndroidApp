package com.miguelmialdea.taskquest.features.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miguelmialdea.taskquest.R
import com.miguelmialdea.taskquest.ui.theme.TaskQuestTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    var showLogo by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }

    // Animaciones
    val logoScale by animateFloatAsState(
        targetValue = if (showLogo) 1f else 0.5f,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (showText) 1f else 0f,
        animationSpec = tween(1000)
    )

    LaunchedEffect(Unit) {
        showLogo = true
        delay(500)
        showText = true
        delay(2000)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4C1D95), // Morado oscuro principal
                        Color(0xFF1E3A8A)  // Azul oscuro
                    )
                )
            )
    ) {
        // Logo central con el escudo
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -50.dp)
                .size(160.dp)
                .scale(logoScale)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "TaskQuest Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Texto debajo del logo
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 80.dp)
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
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(
    name = "Splash Screen",
    showSystemUi = true,
    device = Devices.PIXEL_4
)
@Composable
fun SplashScreenPreview() {
    TaskQuestTheme {
        Surface {
            SplashScreen(
                onSplashFinished = {}
            )
        }
    }
}

// Preview de la animación en diferentes estados
@Preview(
    name = "Splash States",
    device = Devices.PIXEL_4,
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
fun SplashScreenStatesPreview() {
    TaskQuestTheme {
        Column {
            // Estado inicial
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                SplashScreenContent(
                    showLogo = false,
                    showText = false
                )
            }

            // Estado logo visible
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                SplashScreenContent(
                    showLogo = true,
                    showText = false
                )
            }

            // Estado final
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                SplashScreenContent(
                    showLogo = true,
                    showText = true
                )
            }
        }
    }
}

// Contenido extraído para la preview de estados
@Composable
private fun SplashScreenContent(
    showLogo: Boolean,
    showText: Boolean
) {
    val logoScale by animateFloatAsState(
        targetValue = if (showLogo) 1f else 0.5f,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (showText) 1f else 0f,
        animationSpec = tween(1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4C1D95),
                        Color(0xFF1E3A8A)
                    )
                )
            )
    ) {
        // Logo central con el escudo
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -50.dp)
                .size(160.dp)
                .scale(logoScale)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "TaskQuest Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Texto debajo del logo
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 80.dp)
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
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}