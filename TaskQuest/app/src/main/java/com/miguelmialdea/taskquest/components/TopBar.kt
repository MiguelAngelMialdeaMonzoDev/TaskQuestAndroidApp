package com.miguelmialdea.taskquest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String,
    showBackArrow: Boolean = true,
    showCloseButton: Boolean = true,
    onBackClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF4A148C)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Espacio o botón back
        Box(modifier = Modifier.size(48.dp)) {
            if (showBackArrow) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Botón regresar",
                        tint = Color.White
                    )
                }
            }
        }

        // Título
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        // Espacio o botón cerrar
        Box(modifier = Modifier.size(48.dp)) {
            if (showCloseButton) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Botón cerrar",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(name = "TopBar - Todos los elementos visibles")
@Composable
fun CustomTopBarPreview() {
    MaterialTheme {
        TopBar(
            title = "TaskQuest",
            showBackArrow = true,
            showCloseButton = true
        )
    }
}

@Preview(name = "TopBar - Solo flecha back")
@Composable
fun CustomTopBarOnlyBackPreview() {
    MaterialTheme {
        TopBar(
            title = "Misiones Diarias",
            showBackArrow = true,
            showCloseButton = false
        )
    }
}

@Preview(name = "TopBar - Solo botón cerrar")
@Composable
fun CustomTopBarOnlyClosePreview() {
    MaterialTheme {
        TopBar(
            title = "Configuración",
            showBackArrow = false,
            showCloseButton = true
        )
    }
}

@Preview(name = "TopBar - Solo título")
@Composable
fun CustomTopBarOnlyTitlePreview() {
    MaterialTheme {
        TopBar(
            title = "TaskQuest",
            showBackArrow = false,
            showCloseButton = false
        )
    }
}

@Preview(name = "TopBar - Título largo")
@Composable
fun CustomTopBarLongTitlePreview() {
    MaterialTheme {
        TopBar(
            title = "Este es un título muy largo para probar el comportamiento",
            showBackArrow = true,
            showCloseButton = true
        )
    }
}