package com.miguelmialdea.taskquest.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CreateQuestDialog(
    onDismiss: () -> Unit,
    onQuestCreated: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var timeRange by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var xpReward by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF2D2438),
        title = {
            Text(
                text = "Create New Quest",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Quest Title", color = Color.White) },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = Color.White,
                        focusedPlaceholderColor = Color.White,
                        unfocusedContainerColor = Color(0xFF362F45),
                        focusedContainerColor = Color(0xFF362F45),
                        focusedIndicatorColor = Color(0xFF7E57C2),
                        unfocusedIndicatorColor = Color(0xFF4A148C),
                        cursorColor = Color(0xFF7E57C2),
                        focusedLabelColor = Color(0xFF7E57C2),
                        unfocusedLabelColor = Color(0xFFB39DDB)
                    )
                )

                OutlinedTextField(
                    value = timeRange,
                    onValueChange = { timeRange = it },
                    label = { Text("Time Range", color = Color.White) },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = Color.White,
                        focusedPlaceholderColor = Color.White,
                        unfocusedContainerColor = Color(0xFF362F45),
                        focusedContainerColor = Color(0xFF362F45),
                        focusedIndicatorColor = Color(0xFF7E57C2),
                        unfocusedIndicatorColor = Color(0xFF4A148C),
                        cursorColor = Color(0xFF7E57C2),
                        focusedLabelColor = Color(0xFF7E57C2),
                        unfocusedLabelColor = Color(0xFFB39DDB)
                    )
                )

                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Quest Type", color = Color.White) },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = Color.White,
                        focusedPlaceholderColor = Color.White,
                        unfocusedContainerColor = Color(0xFF362F45),
                        focusedContainerColor = Color(0xFF362F45),
                        focusedIndicatorColor = Color(0xFF7E57C2),
                        unfocusedIndicatorColor = Color(0xFF4A148C),
                        cursorColor = Color(0xFF7E57C2),
                        focusedLabelColor = Color(0xFF7E57C2),
                        unfocusedLabelColor = Color(0xFFB39DDB)
                    )
                )

                OutlinedTextField(
                    value = xpReward,
                    onValueChange = { xpReward = it },
                    label = { Text("XP Reward", color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = Color.White,
                        focusedPlaceholderColor = Color.White,
                        unfocusedContainerColor = Color(0xFF362F45),
                        focusedContainerColor = Color(0xFF362F45),
                        focusedIndicatorColor = Color(0xFF7E57C2),
                        unfocusedIndicatorColor = Color(0xFF4A148C),
                        cursorColor = Color(0xFF7E57C2),
                        focusedLabelColor = Color(0xFF7E57C2),
                        unfocusedLabelColor = Color(0xFFB39DDB)
                    )
                )

                OutlinedTextField(
                    value = tags,
                    onValueChange = { tags = it },
                    label = { Text("Tags (comma separated)", color = Color.White) },
                    colors = TextFieldDefaults.colors(
                        unfocusedPlaceholderColor = Color.White,
                        focusedPlaceholderColor = Color.White,
                        unfocusedContainerColor = Color(0xFF362F45),
                        focusedContainerColor = Color(0xFF362F45),
                        focusedIndicatorColor = Color(0xFF7E57C2),
                        unfocusedIndicatorColor = Color(0xFF4A148C),
                        cursorColor = Color(0xFF7E57C2),
                        focusedLabelColor = Color(0xFF7E57C2),
                        unfocusedLabelColor = Color(0xFFB39DDB)
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Aquí implementarías la lógica para crear la quest
                    onQuestCreated()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2)
                )
            ) {
                Text("Create Quest")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFB39DDB)
                )
            ) {
                Text("Cancel")
            }
        }
    )
}