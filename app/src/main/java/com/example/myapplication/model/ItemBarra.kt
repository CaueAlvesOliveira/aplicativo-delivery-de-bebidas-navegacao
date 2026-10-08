package com.example.myapplication.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ItemBarra(
    val nome: String,
    val icone: ImageVector,
    val onClick: () -> Unit = {}
)
