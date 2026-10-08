package com.example.myapplication.model

data class DadosFormProduto(
    val nome: String,
    val volume: String,
    val estabelecimento: String,
    val precoTexto: String,
    val descontoTexto: String,
    val descricao: String,
    val imagem: Int,
    val categoriaId: Int
)
