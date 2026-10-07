package com.example.myapplication.model

data class ItemBarra(
    val nome: String,
    val icone: Int,
    val onClick: () -> Unit = {}
)
