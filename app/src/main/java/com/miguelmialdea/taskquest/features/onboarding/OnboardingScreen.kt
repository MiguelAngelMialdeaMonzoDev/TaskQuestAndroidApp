package com.miguelmialdea.taskquest.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miguelmialdea.taskquest.R
import com.miguelmialdea.taskquest.ui.theme.TaskQuestTheme
import org.koin.androidx.compose.koinViewModel

// Pantalla principal
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel(),
    onOnboardingComplete: () -> Unit
) {
    val currentPage by viewModel.currentPage.collectAsState()
    val pages = viewModel.pages

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

    // Sincronizar pagerState con ViewModel
    LaunchedEffect(currentPage) {
        pagerState.animateScrollToPage(currentPage)
    }

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != currentPage) {
            viewModel.setCurrentPage(pagerState.currentPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pages[currentPage].backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Indicadores de página
            PageIndicators(
                currentPage = currentPage,
                pageCount = pages.size
            )

            // Contenido principal
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                OnboardingPageContent(
                    page = pages[page]
                )
            }

            // Botones de navegación
            NavigationButtons(
                currentPage = currentPage,
                pageCount = pages.size,
                onPrevious = { viewModel.onPreviousPage() },
                onNext = {
                    if (currentPage == pages.size - 1) {
                        onOnboardingComplete()
                    } else {
                        viewModel.onNextPage()
                    }
                }
            )
        }
    }
}

@Composable
private fun PageIndicators(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width(if (currentPage == index) 24.dp else 8.dp)
                    .height(8.dp)
                    .background(
                        color = Color.White.copy(
                            alpha = if (currentPage == index) 1f else 0.5f
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

@Composable
private fun OnboardingPageContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.iconRes),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            colorFilter = ColorFilter.tint(page.iconColor)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NavigationButtons(
    currentPage: Int,
    pageCount: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentPage > 0) {
            TextButton(
                onClick = onPrevious
            ) {
                Text(
                    text = "Anterior",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            Spacer(modifier = Modifier.width(64.dp))
        }

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF4C1D95)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = if (currentPage == pageCount - 1) "¡Comenzar!" else "Siguiente",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}

// Preview de páginas individuales
@Preview(
    name = "Onboarding Page - Welcome",
    showBackground = true,
    backgroundColor = 0xFF4C1D95,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun OnboardingPageWelcomePreview() {
    TaskQuestTheme {
        OnboardingPageContent(
            page = OnboardingPage(
                title = "¡Bienvenido a tu aventura!",
                description = "Convierte tus tareas diarias en misiones épicas y alcanza tus objetivos como un verdadero héroe.",
                iconRes = R.drawable.ic_trophy,
                backgroundColor = Color(0xFF4C1D95),
                iconColor = Color(0xFFFFD700)
            )
        )
    }
}

@Preview(
    name = "Onboarding Page - Missions",
    showBackground = true,
    backgroundColor = 0xFF1E3A8A,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun OnboardingPageMissionsPreview() {
    TaskQuestTheme {
        OnboardingPageContent(
            page = OnboardingPage(
                title = "Misiones Diarias",
                description = "Completa misiones, gana experiencia y sube de nivel mientras mejoras tu productividad.",
                iconRes = R.drawable.ic_target,
                backgroundColor = Color(0xFF1E3A8A),
                iconColor = Color(0xFF93C5FD)
            )
        )
    }
}

@Preview(
    name = "Onboarding Page - Battle Mode",
    showBackground = true,
    backgroundColor = 0xFF4C1D95,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun OnboardingPageBattleModePreview() {
    TaskQuestTheme {
        OnboardingPageContent(
            page = OnboardingPage(
                title = "Modo Batalla",
                description = "Enfréntate a la procrastinación con nuestro timer especial y desbloquea power-ups únicos.",
                iconRes = R.drawable.ic_timer,
                backgroundColor = Color(0xFF4C1D95),
                iconColor = Color(0xFF4ADE80)
            )
        )
    }
}

@Preview(
    name = "Onboarding Page - Start",
    showBackground = true,
    backgroundColor = 0xFF4C1D95,
    widthDp = 360,
    heightDp = 640
)
@Composable
fun OnboardingPageStartPreview() {
    TaskQuestTheme {
        OnboardingPageContent(
            page = OnboardingPage(
                title = "¡Comienza tu aventura!",
                description = "¿Estás listo para convertirte en el héroe de tu productividad?",
                iconRes = R.drawable.ic_sparkle,
                backgroundColor = Color(0xFF4C1D95),
                iconColor = Color(0xFFE879F9)
            )
        )
    }
}