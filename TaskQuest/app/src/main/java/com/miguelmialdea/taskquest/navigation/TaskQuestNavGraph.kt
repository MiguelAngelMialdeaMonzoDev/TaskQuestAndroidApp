package com.miguelmialdea.taskquest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miguelmialdea.taskquest.features.onboarding.OnboardingScreen
import com.miguelmialdea.taskquest.features.splash.SplashScreen

@Composable
fun TaskQuestNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.Splash.path
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Splash
        composable(Route.Splash.path) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Route.Onboarding.path) {
                        popUpTo(Route.Splash.path) { inclusive = true }
                    }
                }
            )
        }
        // Onboarding
        composable(Route.Onboarding.path) {
            OnboardingScreen(
                onOnboardingComplete = {
                    navController.navigate(Route.Home.path) {
                        popUpTo(Route.Onboarding.path) { inclusive = true }
                    }
                }
            )
        }
        // Home
        //composable(Route.Home.path) { HomeScreen(navController = navController) }*/

    }
}