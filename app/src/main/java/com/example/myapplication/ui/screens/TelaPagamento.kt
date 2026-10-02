package com.example.myapplication.ui.screens

import android.icu.text.ListFormatter
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlin.random.Random


@Composable
@Preview
fun TelaPagamento(){

    Scaffold(){ innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(

            ) {
                BotaoVoltar()
                TextoTitulo()
            }

            CardsModeloPagamento()
            CardResumo(1,1f,0f,1f,)
            BotaoConfirmarPagamento()
        }
    }


}

@Composable
fun TextoTitulo(){
    Text(
        "Pagamento",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun CardsModeloPagamento() {

    var itemSelecionado by remember { mutableStateOf(0) }

    val itens = listOf(
        Triple("Pix", R.drawable.add_24, 0),
        Triple("Cartão de Crédito", R.drawable.search_24, 1),
        Triple("Dinheiro na Entrega", R.drawable.list_24, 2),
    )

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        itens.forEach { (nome, icone, index) ->

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .border(
                        width = if (itemSelecionado == index) 2.dp else 1.dp,
                        color = if (itemSelecionado == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { itemSelecionado = index },
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = icone),
                        contentDescription = nome,
                        modifier = Modifier.size(24.dp),
                        tint = if (itemSelecionado == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = nome,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun CardResumo(quantidadeItens: Int, valorTotalProdutos: Float, valorEntrega: Float, valorTotal: Float){

    var itemSelecionado by remember { mutableStateOf(0) }

    Spacer(modifier = Modifier.width(25.dp))
    Column(
        modifier = Modifier.padding(17.dp)
    ) {
        Text(
            "Resumo",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .border(
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp),
                    width = 1.dp
                ),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(16.dp))

                Column() {
                    Row(
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Text(text = "$quantidadeItens itens")
                        Spacer(modifier = Modifier.width(215.dp))
                        Text("R$ $valorTotalProdutos")
                    }

                    Row(
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Text(text = "Entrega expressa")
                        Spacer(modifier = Modifier.width(150.dp))
                        Text("R$ $valorEntrega")
                    }

                    Row(
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Text(
                            text = "Total",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(220.dp))
                        Text("R$ $valorTotal")
                    }
                }

            }
        }
    }
}

@Composable
fun BotaoConfirmarPagamento(){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {}
        ) {
            Text("Confirmar Pedido")
        }


        Row() {
            Spacer(modifier = Modifier.width(10.dp))
            //Icon(imageVector = Icons.Default.Lock, "cadeado")
            Text("Pagamento Seguro")
        }


    }


}