package com.example.myapplication.navegacao

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.TelaCarrinho
import com.example.myapplication.ui.screens.TelaEntrega
import com.example.myapplication.ui.screens.TelaPagamento
import com.example.myapplication.ui.screens.TelaProduto
import com.example.myapplication.ui.screens.TelaProdutoPorCategoria
import com.example.myapplication.ui.screens.TelaRastreio
import com.example.myapplication.ui.screens.telaInicio

@Composable
fun NavagacaoEntreTela() {

    val navInterno = rememberNavController()

    NavHost(
        navController = navInterno,
        startDestination = "home"
    ) {
        composable("home"){ telaInicio() }
        composable("produto"){ TelaProduto() }
        //composable("perfil"){ telaInicio() }
        composable("carrinho"){ TelaCarrinho() }
        composable("pagamento"){ TelaPagamento() }
        composable("entrega"){ TelaEntrega() }
        composable("rastreio"){ TelaRastreio() }
        composable("produtosPorCategoria"){ TelaProdutoPorCategoria() }
    }

}