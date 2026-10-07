package com.example.myapplication.navegacao.rotas

object Rotas {
    const val HOME = "home"
    const val PRODUTO = "produto/{id}"
    //const val PERFIL = "perfil"
    const val CARRINHO = "carrinho"
    const val PAGAMENTO = "pagamento"
    const val ENTREGA = "entrega"
    const val RASTREIO = "rastreio"
    const val PRODUTOS_POR_CATEGORIA = "produtosPorCategoria"

    fun produto(id: Int): String {
        return "produto/$id"
    }
}