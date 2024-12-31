package com.miguel.todolist.presentation.components.textinput

import androidx.compose.ui.text.AnnotatedString

data class TextInputInteraction(
    val textInputBehaviour: String,
    val onTextChanged: (AnnotatedString) -> Unit,
    val onTextPasted: (AnnotatedString) -> Unit
)