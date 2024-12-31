package com.miguel.todolist.presentation.components.textinput

data class TextInputAccessibility (
    val importantForAccessibility: Boolean = false,
    val fontScaling: Boolean = false,
    val labelContentDescription: String? = null,
    val textContentDescription: String? = null,
    val placeHolderContentDescription: String? = null,
    val helperTextContentDescription: String? = null,
    val counterTextContentDescription: String? = null
)