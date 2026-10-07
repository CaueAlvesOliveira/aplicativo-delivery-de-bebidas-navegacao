package com.example.myapplication.navegacao

import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navegacao.rotas.Rotas
import com.example.myapplication.ui.screens.TelaCarrinho
import com.example.myapplication.ui.screens.TelaEntrega
import com.example.myapplication.ui.screens.TelaFormCategoria
import com.example.myapplication.ui.screens.TelaFormProduto
import com.example.myapplication.ui.screens.TelaListaCategorias
import com.example.myapplication.ui.screens.TelaListaProdutos
import com.example.myapplication.ui.screens.TelaPagamento
import com.example.myapplication.ui.screens.TelaProduto
import com.example.myapplication.ui.screens.TelaProdutoPorCategoria
import com.example.myapplication.ui.screens.TelaRastreio
import com.example.myapplication.ui.screens.telaInicio
import com.example.myapplication.viewmodel.LojaViewModel

@Composable
fun NavagacaoEntreTela() {

    val navInterno = rememberNavController()

    val viewModel: LojaViewModel = viewModel()

    NavHost(
        navController = navInterno,
        startDestination = Rotas.HOME
    ) {
        composable(Rotas.HOME){ telaInicio(navInterno, viewModel) }
        composable(Rotas.PRODUTO){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            TelaProduto(
                id,
                viewModel,
                onVoltar = {navInterno.popBackStack()},
                irParaCarrinho = {navInterno.navigate(Rotas.CARRINHO)}
            )
        }
        composable(Rotas.PRODUTOS){
            TelaListaProdutos(
                viewModel,
                onVoltar = {navInterno.popBackStack()},
                onNovo = {navInterno.navigate(Rotas.formProduto())},
                onEditar = {id -> navInterno.navigate(Rotas.formProduto(id))})
        }
        composable(Rotas.FORM_PRODUTO){backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            TelaFormProduto(
                id,
                viewModel,
                onVoltar = {navInterno.popBackStack()})
        }
        //composable("perfil"){ telaInicio() }
        composable(Rotas.CARRINHO){ TelaCarrinho() }
        composable(Rotas.PAGAMENTO){ TelaPagamento(onVoltar = {navInterno.popBackStack()}) }
        composable(Rotas.ENTREGA){ TelaEntrega() }
        composable(Rotas.RASTREIO){ TelaRastreio(onVoltar = {navInterno.popBackStack()}) }
        composable(Rotas.CATEGORIAS) {
            TelaListaCategorias(
                viewModel,
                onVoltar = { navInterno.popBackStack() },
                onNovo = { navInterno.navigate(Rotas.formCategoria()) },
                onEditar = { id -> navInterno.navigate(Rotas.formCategoria(id)) },
                onAbrir = { id -> navInterno.navigate(Rotas.produtosPorCategoria(id)) }
            )
        }
        composable(Rotas.FORM_CATEGORIA) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            TelaFormCategoria(
                id,
                viewModel,
                onVoltar = { navInterno.popBackStack() }
            )
        }

        composable(Rotas.PRODUTOS_POR_CATEGORIA) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            TelaProdutoPorCategoria(
                id,
                viewModel,
                onVoltar = { navInterno.popBackStack() },
                onProdutoClick = { produtoId -> navInterno.navigate(Rotas.produto(produtoId)) }
            )
        }
    }
}