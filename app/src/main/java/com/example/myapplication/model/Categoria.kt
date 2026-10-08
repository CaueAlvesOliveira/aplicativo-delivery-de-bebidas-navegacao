package com.example.myapplication.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Categoria(
    val id: Int,
    val nome: String,
    val icone: ImageVector,
    val cor: Color
)
