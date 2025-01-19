package com.miguelmialdea.taskquest.navigation

sealed class Route(val path: String) {
    // Base routes
    data object Splash : Route("splash")
    data object Onboarding : Route("onboarding")
    data object Home : Route("home")
    data object Focus : Route("focus")
    data object Tasks : Route("tasks")
    data object Profile : Route("profile")

    companion object {
        val bottomNavRoutes = listOf(Home, Focus, Tasks, Profile)
    }
}