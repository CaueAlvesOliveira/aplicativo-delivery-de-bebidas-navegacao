package com.example.myapplication.model

data class ErrosFormProduto(
    val nome: String? = null,
    val estabelecimento: String? = null,
    val preco: String? = null,
    val categoria: String? = null
) {
    val temErro: Boolean
        get() = nome != null || estabelecimento != null || preco != null || categoria != null
}
