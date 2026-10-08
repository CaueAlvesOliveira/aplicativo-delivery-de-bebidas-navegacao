package com.example.myapplication.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Liquor
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material.icons.outlined.SportsBar
import androidx.compose.material.icons.outlined.WineBar
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.model.Categoria
import com.example.myapplication.model.ItemCarrinho
import com.example.myapplication.model.Produto

class LojaViewModel : ViewModel() {

    val produtos = mutableStateListOf<Produto>()

    val categorias = mutableStateListOf<Categoria>()

    val carrinho = mutableStateListOf<ItemCarrinho>()

    init {

        adicionarCategoria("Cerveja", Icons.Outlined.SportsBar, Color(0xFF29B6F6))
        adicionarCategoria("Vinho", Icons.Outlined.WineBar, Color(0xFF7E57C2))
        adicionarCategoria("Destilada", Icons.Outlined.Liquor, Color(0xFFFF7043))
        adicionarCategoria("Gelo", Icons.Outlined.AcUnit, Color(0xFF66BB6A))
        adicionarCategoria("Refrigerante", Icons.Outlined.LocalDrink, Color(0xFF8D6E63))

        val cerveja = idDaCategoria("Cerveja")
        val vinho = idDaCategoria("Vinho")
        val destilada = idDaCategoria("Destilada")
        val gelo = idDaCategoria("Gelo")
        val refrigerante = idDaCategoria("Refrigerante")

        adicionarProduto("Vodka", "1L", "Distribuidora Polar", 45.90, R.drawable.vodka, "20", "Destilado cristalino de sabor limpo. Base perfeita para drinks e caipiroskas.", destilada)
        adicionarProduto("Gin", "750ml", "Botânico Gin Club", 79.90, R.drawable.gin, "10", "Notas de zimbro com toques cítricos e botânicos. Combina com tônica gelada e uma rodela de limão.", destilada)
        adicionarProduto("Cachaça", "700ml", "Alambique Serra Dourada", 24.90, R.drawable.cachaca, "30", "Cachaça de sabor suave e toque adocicado de madeira. Ótima pura ou na caipirinha.", destilada)
        adicionarProduto("Coca Cola", "2L", "Mercearia Dois Irmãos", 9.90, R.drawable.refrigerante, "35", "Refrigerante sabor cola, bem gelado e com gás. Perfeito para acompanhar pizza ou lanche.", refrigerante)
    }

    fun produtosOferta(): List<Produto> {
        return produtos.filter { !it.desconto.isNullOrBlank() }
    }

    fun produtosNormais(): List<Produto> {
        return produtos.filter { it.desconto.isNullOrBlank() }
    }

    fun adicionarProduto(
        nome: String,
        volume: String,
        estabelecimento: String,
        preco: Double,
        imagem: Int,
        desconto: String,
        descricao: String,
        categoriaId: Int) {
        produtos.add(Produto(gerarIdDoProduto(), nome, volume, estabelecimento, preco, imagem, desconto, descricao, categoriaId))
    }

    fun adicionarProduto(
        nome: String,
        volume: String,
        estabelecimento: String,
        preco: Double,
        imagem: Int,
        descricao: String,
        categoriaId: Int) {
        produtos.add(Produto(gerarIdDoProduto(), nome, volume, estabelecimento, preco, imagem, null, descricao, categoriaId))
    }

    fun removerProduto(id: Int) {
        produtos.removeAll { it.id == id }
    }

    fun editarProduto(
        id: Int,
        nome: String,
        volume: String,
        estabelecimento: String,
        preco: Double,
        imagem: Int,
        desconto: String?,
        descricao: String,
        categoriaId: Int) {
        val i = produtos.indexOfFirst { it.id == id }
        if (i != -1) produtos[i] = produtos[i].copy(
            nome = nome,
            volume = volume,
            estabelecimento = estabelecimento,
            preco = preco,
            imagem = imagem,
            desconto = desconto,
            descricao = descricao,
            categoriaId = categoriaId
        )
    }

    fun buscarProduto(id: Int): Produto? {
        return produtos.find { it.id == id }
    }

    fun adicionarCategoria(nome: String, icone: ImageVector, cor: Color) {
        categorias.add(Categoria(gerarIdDaCategoria(), nome, icone, cor))
    }

    fun removerCategoria(id: Int) {
        categorias.removeAll { it.id == id }
        produtos.removeAll { it.categoriaId == id }
    }

    fun editarCategoria(id: Int, nome: String, icone: ImageVector, cor: Color) {
        val i = categorias.indexOfFirst { it.id == id }
        if (i != -1) categorias[i] = categorias[i].copy(
            nome = nome, icone = icone, cor = cor
        )
    }

    fun buscarCategoria(id: Int): Categoria? {
        return categorias.find { it.id == id }
    }

    fun adicionarAoCarrinho(produtoId: Int, quantidade: Int = 1) {
        val i = carrinho.indexOfFirst { it.produtoId == produtoId }
        if (i == -1) {
            carrinho.add(ItemCarrinho(produtoId, quantidade))
        } else {
            carrinho[i] = carrinho[i].copy(quantidade = carrinho[i].quantidade + quantidade)
        }
    }

    fun alterarQuantidadeNoCarrinho(produtoId: Int, quantidade: Int) {
        if (quantidade <= 0) { removerDoCarrinho(produtoId); return }
        val i = carrinho.indexOfFirst { it.produtoId == produtoId }
        if (i != -1) carrinho[i] = carrinho[i].copy(quantidade = quantidade)
    }

    fun removerDoCarrinho(produtoId: Int) {
        carrinho.removeAll { it.produtoId == produtoId }
    }

    fun subtotalCarrinho(): Double =
        carrinho.sumOf { (buscarProduto(it.produtoId)?.preco ?: 0.0) * it.quantidade }

    private fun gerarIdDoProduto(): Int {
        if (produtos.isEmpty()) {
            return 1
        }
        return produtos.last().id + 1
    }

    private fun gerarIdDaCategoria(): Int {
        if (categorias.isEmpty()) {
            return 1
        }
        return categorias.last().id + 1
    }

    private fun idDaCategoria(nome: String): Int {
        return categorias.first { it.nome == nome }.id
    }

    fun calculaQuantidadeItensComprados(): Int {
        var quantidade = 0
        itensComprados.forEach {
            quantidade += it.quantidadeItens
        }
        return quantidade
    }

    fun calculaValorTotalItensComprados(): Double {
        var valorTotal = 0.0
        itensComprados.forEach {
            valorTotal += it.quantidadeItens * it.valorTotal
        }
        return valorTotal
    }

    fun calculaValorDaEntrega(): Double {
        val valorEntrega = 10.99
        return valorEntrega
    }

    fun calculaValorTotal(): Double {
        val valorTotal = calculaValorTotalItensComprados() + calculaValorDaEntrega()
        return valorTotal
    }
}