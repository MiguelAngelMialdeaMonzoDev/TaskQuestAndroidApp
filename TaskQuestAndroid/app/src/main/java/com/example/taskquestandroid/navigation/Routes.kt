package com.example.taskquestandroid.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object Goals : Screen("goals")
    object Profile : Screen("profile")
    object Settings : Screen("settings")

    // Rutas con parámetros
    object TaskDetail : Screen("task/{taskId}") {
        fun createRoute(taskId: String) = "task/$taskId"
    }

    object GoalDetail : Screen("goal/{goalId}") {
        fun createRoute(goalId: String) = "goal/$goalId"
    }
}