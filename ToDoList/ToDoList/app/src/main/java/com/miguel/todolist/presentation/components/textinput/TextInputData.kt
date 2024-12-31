package com.miguel.todolist.presentation.components.textinput

import androidx.compose.ui.text.AnnotatedString

data class TextInputData(
    val label: AnnotatedString,
    val text: AnnotatedString,
    val placerHolder: AnnotatedString,
    val icon: Int,
    val rightIcon: Int,
    val helperText: AnnotatedString,
    val helperIcon: Int,
    val maxTextLength: Int
)