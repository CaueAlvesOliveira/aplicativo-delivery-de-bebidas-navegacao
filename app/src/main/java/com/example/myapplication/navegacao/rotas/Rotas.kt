package com.example.myapplication.navegacao.rotas

object Rotas {
    const val HOME = "home"
    const val PRODUTO = "produto/{id}"
    //const val PERFIL = "perfil"
    const val CARRINHO = "carrinho"
    const val PAGAMENTO = "pagamento"
    const val RASTREIO = "rastreio"
    const val PRODUTOS_POR_CATEGORIA = "produtosPorCategoria/{id}"
    const val PRODUTOS = "produtos"
    const val FORM_PRODUTO = "formProduto/{id}"
    const val CATEGORIAS = "categorias"
    const val FORM_CATEGORIA = "formCategoria/{id}"

    fun produto(id: Int): String {
        return "produto/$id"
    }

    fun formProduto(id: Int = -1): String {
        return "formProduto/$id"
    }

    fun produtosPorCategoria(id: Int): String {
        return "produtosPorCategoria/$id"
    }

    fun formCategoria(id: Int = -1): String {
        return "formCategoria/$id"
    }
}