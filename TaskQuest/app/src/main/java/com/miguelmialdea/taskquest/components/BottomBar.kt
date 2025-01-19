package com.miguelmialdea.taskquest.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.miguelmialdea.taskquest.navigation.Route

@Composable
fun BottomBar(
    currentRoute: String?,
    onRouteSelected: (String) -> Unit
) {
    NavigationBar {
        Route.bottomNavRoutes.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.path,
                onClick = { onRouteSelected(screen.path) },
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            Route.Home -> Icons.Rounded.Home
                            Route.Focus -> Icons.Rounded.DateRange
                            Route.Tasks -> Icons.Rounded.CheckCircle
                            Route.Profile -> Icons.Rounded.Person
                            else -> Icons.Rounded.Home
                        },
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        when (screen) {
                            Route.Home -> "Inicio"
                            Route.Focus -> "Focus"
                            Route.Tasks -> "Tareas"
                            Route.Profile -> "Perfil"
                            else -> ""
                        }
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun TaskQuestBottomBarPreview() {
    BottomBar(
        currentRoute = Route.Home.path,
        onRouteSelected = {}
    )
}