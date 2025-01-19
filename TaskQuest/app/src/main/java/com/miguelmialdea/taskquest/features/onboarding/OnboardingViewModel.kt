package com.miguelmialdea.taskquest.features.onboarding


import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.miguelmialdea.taskquest.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Modelo de datos para cada página
data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val iconRes: Int,
    val backgroundColor: Color,
    val iconColor: Color
)

// ViewModel
class OnboardingViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    private val _pages = listOf(
        OnboardingPage(
            title = "¡Bienvenido a tu aventura!",
            description = "Convierte tus tareas diarias en misiones épicas y alcanza tus objetivos como un verdadero héroe.",
            iconRes = R.drawable.ic_trophy,
            backgroundColor = Color(0xFF4C1D95),
            iconColor = Color(0xFFFFD700)
        ),
        OnboardingPage(
            title = "Misiones Diarias",
            description = "Completa misiones, gana experiencia y sube de nivel mientras mejoras tu productividad.",
            iconRes = R.drawable.ic_target,
            backgroundColor = Color(0xFF1E3A8A),
            iconColor = Color(0xFF93C5FD)
        ),
        OnboardingPage(
            title = "Modo Batalla",
            description = "Enfréntate a la procrastinación con nuestro timer especial y desbloquea power-ups únicos.",
            iconRes = R.drawable.ic_timer,
            backgroundColor = Color(0xFF4C1D95),
            iconColor = Color(0xFF4ADE80)
        ),
        OnboardingPage(
            title = "¡Comienza tu aventura!",
            description = "¿Estás listo para convertirte en el héroe de tu productividad?",
            iconRes = R.drawable.ic_sparkle,
            backgroundColor = Color(0xFF4C1D95),
            iconColor = Color(0xFFE879F9)
        )
    )
    val pages: List<OnboardingPage> = _pages

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    fun onNextPage() {
        if (_currentPage.value < pages.size - 1) {
            _currentPage.value++
        }
    }

    fun onPreviousPage() {
        if (_currentPage.value > 0) {
            _currentPage.value--
        }
    }
}
