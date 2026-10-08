package com.example.myapplication.model

data class Produto(
    val id: Int,
    val nome: String,
    val volume: String,
    val estabelecimento: String,
    val preco: Double,
    val imagem: Int,
    val desconto: String? = null,
    val descricao: String,
    val categoriaId: Int
)
