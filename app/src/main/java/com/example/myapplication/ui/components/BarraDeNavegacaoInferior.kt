package com.example.myapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Liquor
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.myapplication.model.ItemBarra

@Composable
fun BarraDeNavegacaoInferior(
    selectedIndex: Int = 0,
    onInicio: () -> Unit = {},
    onCarrinho: () -> Unit = {},
    onPedidos: () -> Unit = {},
    onProdutos: () -> Unit = {},
    onPerfil: () -> Unit = {}
) {
    var itemSelecionado by remember { mutableIntStateOf(selectedIndex) }

    val itens = listOf(
        ItemBarra("Início", Icons.Outlined.Home, onInicio),
        ItemBarra("Carrinho", Icons.Outlined.ShoppingCart, onCarrinho),
        ItemBarra("Pedidos", Icons.AutoMirrored.Filled.List, onPedidos),
        ItemBarra("Produtos", Icons.Outlined.Liquor, onProdutos),
        ItemBarra("Perfil", Icons.Outlined.Person, onPerfil)
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        itens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = itemSelecionado == index,
                onClick = {
                    itemSelecionado = index
                    item.onClick()
                },
                icon = {
                    Icon(
                        imageVector = item.icone,
                        contentDescription = item.nome
                    )
                },
                label = {
                    Text(text = item.nome)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFF7043),
                    selectedTextColor = Color(0xFFFF7043),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
