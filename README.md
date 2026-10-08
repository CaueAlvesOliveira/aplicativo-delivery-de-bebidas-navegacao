# Delivery de Bebidas - MAF (Mínimo Aplicativo Funcional)

Aplicativo Android de **delivery de bebidas**, desenvolvido em **Kotlin + Jetpack Compose** para a disciplina de **Desenvolvimento de Aplicativos Móveis**.

Este repositório é a **segunda parte do Trabalho** da A2 e dá continuação ao Trabalho 1. Aqui o app deixou de ser só visual: ele tem **navegação real**, **listas reativas**, **cadastro/edição/remoção de itens**, **telas de detalhes** e um **carrinho** que realmente soma valores.

---

## Sumário

1. [Objetivos](#objetivos)
2. [Figma (Trabalho 1)](#figma-trabalho-1)
3. [Canvas: por que esse aplicativo existe?](#canvas-por-que-esse-aplicativo-existe)
4. [O que o app faz](#o-que-o-app-faz)
5. [Mapa de telas e navegação](#mapa-de-telas-e-navegação)
6. [Requisitos do Trabalho 2: onde cada um foi atendido](#requisitos-do-trabalho-2-onde-cada-um-foi-atendido)
7. [Documentação do processo e das decisões](#documentação-do-processo-e-das-decisões)
8. [Tecnologias utilizadas](#tecnologias-utilizadas)
9. [Estrutura do projeto](#estrutura-do-projeto)
10. [Como executar](#como-executar)
11. [Limitações conhecidas](#limitações-conhecidas)
12. [Anexos](#anexos)
13. [Licença](#licença)

---

## Objetivos

* Desenvolver uma aplicação mobile funcional, com navegação e dados que se movem de verdade;
* Aplicar os conceitos vistos na disciplina: `NavHost`, `LazyColumn`, `Card`, `mutableStateListOf`, formulários, `ViewModel`;
* Documentar o processo e as decisões do trio.

---

## Figma (Trabalho 1)

Board com a UI das 5 telas originais do Trabalho 1:

* Tela de Início
* Tela do Produto
* Tela do Carrinho
* Tela do Pagamento
* Tela de Verificação (rastreio) do pedido

https://www.figma.com/design/FGavv6Oe5wV3DbMC6QftjL/atividade-android?node-id=0-1&t=4b5kjobzLUZ87pgu-1

---

## Canvas: por que esse aplicativo existe?

**Qual problema esse aplicativo resolve? Para quem ele é?**

O app resolve o problema de comprar bebida em cima da hora, quando o usuário já decidiu o que quer e precisa que chegue rápido, sem ter que sair de casa ou ir a um mercado físico.

**Por que alguém abriria esse aplicativo hoje? E por que abriria de novo amanhã?**

Hoje, abriria porque percebeu que está faltando bebida para um evento próximo e precisa resolver rápido. Abriria de novo amanhã se a experiência de pedir tiver sido simples e rápida da primeira vez.

**Qual é a única coisa que o aplicativo precisa fazer bem para "funcionar" na cabeça de quem usa?**

Dar confiança de que o pedido vai chegar rápido e no lugar certo.

**Se fosse um produto de verdade, como ele geraria valor ou dinheiro?**

De forma hipotética, por comissão sobre cada pedido repassada aos estabelecimentos parceiros, por taxa de entrega no checkout, e por destaque pago de produtos, um espaço que fornecedores poderiam pagar para aparecer, parecido com o modelo de outros apps de delivery.

**Quais decisões de tela vieram dessas respostas?**

Por isso a tela inicial mostra a categoria e os produtos mais pedidos: o usuário já sabe o que quer, então a prioridade é reduzir cliques até o carrinho, não apresentar o app. Pelo mesmo motivo, a tela de rastreio expõe o tempo estimado de chegada em destaque em vez de detalhes menos urgentes como o histórico completo do pedido.

---

## O que o app faz

* **Início:** categorias em carrossel, banner de frete grátis, "Mais pedidos por aqui" e "Ofertas da Semana" (produtos com desconto). Dá para abrir um produto ou adicioná-lo direto ao carrinho pelo botão **+**.
* **Produtos (lista):** todos os produtos cadastrados. É possível **adicionar** (botão flutuante), **editar**, **remover** (com diálogo de confirmação) e **abrir** o detalhe.
* **Categorias (lista):** todas as categorias, com a quantidade de produtos de cada uma. Também permite **adicionar**, **editar**, **remover** e **abrir** os produtos daquela categoria.
* **Carrinho:** altera a quantidade (e remove ao chegar em zero), calcula subtotal, taxa de entrega e total, e leva ao pagamento.
* **Pagamento → Rastreio:** escolha da forma de pagamento, resumo calculado a partir do carrinho e acompanhamento do pedido.

---

## Mapa de telas e navegação

O app tem **10 telas**, todas registradas em um único `NavHost` (`navegacao/AppNavigation.kt`).

| # | Tela | Rota | Função |
|---|------|------|--------|
| 1 | Início | `home` | Vitrine: categorias, mais pedidos e ofertas |
| 2 | **Lista de Produtos** | `produtos` | Lista + adicionar / editar / remover |
| 3 | **Detalhes do Produto** | `produto/{id}` | Imagem, dados, seletor de quantidade e total calculado |
| 4 | Formulário de Produto | `formProduto/{id}` | Cria (`id = -1`) ou edita um produto |
| 5 | **Lista de Categorias** | `categorias` | Lista + adicionar / editar / remover |
| 6 | **Detalhes da Categoria** | `produtosPorCategoria/{id}` | Produtos pertencentes àquela categoria |
| 7 | Formulário de Categoria | `formCategoria/{id}` | Cria ou edita uma categoria |
| 8 | Carrinho | `carrinho` | Itens, quantidades e resumo do pedido |
| 9 | Pagamento | `pagamento` | Forma de pagamento e confirmação |
| 10 | Rastreio | `rastreio` | Status e tempo estimado do pedido |

A **barra inferior** (`NavigationBar`) leva a **Início, Carrinho, Pedidos (Rastreio) e Produtos** em qualquer tela principal.

---

## Requisitos do Trabalho 2: onde cada um foi atendido

| Requisito (enunciado) | Onde está no código |
|---|---|
| Mínimo de 7 telas navegáveis | 10 telas em `ui/screens/` |
| `NavHost` central + objeto `Rotas` com `const val String` | `navegacao/AppNavigation.kt` e `navegacao/rotas/Rotas.kt` |
| `NavigationBar` funcionando | `ui/components/BarraDeNavegacaoInferior.kt` |
| `navController.navigate(...)` em todos os botões | callbacks (`onInicio`, `onCarrinho`, `onAbrir`, `onNovo`…) ligados no `NavHost` |
| `TopAppBar` com botão de voltar (`popBackStack()`) | `ui/components/Cabecalho.kt` (`TopBarTela` + `BotaoVoltar`), usado nas telas de lista, forms, carrinho, pagamento e rastreio |
| 2 `data class` diferentes | `Produto` e `Categoria` (além de `ItemCarrinho`, `DadosFormProduto`, `ErrosFormProduto`, `ItemBarra`) |
| 2 telas de lista com `LazyColumn` + `Card` + `mutableStateListOf` | `TelaListaProdutos` e `TelaListaCategorias`; as listas vivem em `LojaViewModel` |
| **Adicionar** pela UI (`OutlinedTextField` + `Button`) | `TelaFormProduto` e `TelaFormCategoria` |
| **Remover** / editar pela UI | ícones de lixeira e lápis em cada `Card`, com `AlertDialog` de confirmação |
| Clicar no item abre **Detalhes** com o item certo | `produto/{id}` e `produtosPorCategoria/{id}`: o `id` vai na rota e a tela busca o item no `ViewModel` |
| Detalhes com algo **a mais** que o exemplo de aula | ver [seção 4 da documentação](#4-qual-foi-a-complexidade-extra-na-tela-de-detalhes) |

---



## Tecnologias utilizadas

* **Linguagem:** Kotlin
* **IDE:** Android Studio
* **Plataforma:** Android
* **Interface:** Jetpack Compose (Material 3)
* **Navegação:** Navigation Compose (`NavHost`, `NavController`)
* **Estado:** `ViewModel` + `mutableStateListOf` / `mutableStateOf`

---

## Estrutura do projeto

```text
app/src/main/java/com/example/myapplication/
├── MainActivity.kt
├── model/
│   ├── Produto.kt
│   ├── Categoria.kt
│   ├── ItemCarrinho.kt
│   ├── ItemBarra.kt
│   ├── DadosFormProduto.kt
│   ├── ErrosFormProduto.kt
│   └── EstadoEtapa.kt
├── viewmodel/
│   └── LojaViewModel.kt          # listas reativas, validações e regras do carrinho
├── navegacao/
│   ├── AppNavigation.kt          # NavHost central
│   └── rotas/
│       └── Rotas.kt              # rotas nomeadas (const val)
└── ui/
    ├── components/
    │   ├── BarraDeNavegacaoInferior.kt
    │   └── Cabecalho.kt          # TopBarTela + BotaoVoltar
    ├── screens/
    │   ├── telaInicio.kt
    │   ├── TelaLIstaProdutos.kt
    │   ├── TelaProduto.kt        # detalhes do produto
    │   ├── TelaFormProduto.kt
    │   ├── TelaListaCategorias.kt
    │   ├── TelaProdutoPorCategoria.kt  # detalhes da categoria
    │   ├── TelaFormCategoria.kt
    │   ├── TelaCarrinho.kt
    │   ├── TelaPagamento.kt
    │   └── TelaRastreio.kt
    └── theme/
```
## Como executar

### Pré-requisitos

* [Android Studio](https://developer.android.com/studio)
* Android SDK compatível com o projeto
* JDK compatível com a versão utilizada pelo projeto

### Passo a passo

1. Clone este repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

2. Abra o projeto no **Android Studio**.
3. Aguarde o Gradle sincronizar e baixar as dependências.
4. Conecte um dispositivo Android ou inicie um emulador.
5. Clique em **Run ▶**.

### Roteiro rápido de teste (o mesmo da apresentação)

1. Na barra inferior, abra **Produtos** → toque no **+** → cadastre um produto → ele aparece na lista.
2. Toque na **lixeira** de um produto → confirme → ele some da lista.
3. Toque em um produto (fora dos ícones) → confira que os **detalhes são do item tocado** → mude a quantidade e veja o total mudar → **Adicionar ao carrinho**.
4. Abra **Início → Gerenciar** (Categorias) → adicione, edite e remova uma categoria.
5. Toque em uma categoria → veja somente os produtos dela.
6. No **Carrinho**, altere quantidades → **Ir para pagamento** → **Confirmar pedido** → veja o **Rastreio**.
7. Navegue por todas as abas da barra inferior e use o botão de voltar.

---

## Limitações conhecidas

Como previsto no enunciado (seção 5), os dados vivem **apenas em memória**: ao fechar o app, o que foi adicionado se perde, e a persistência fica para o próximo trabalho. Também são limitações desta versão:

* O **Rastreio é estático** (pedido nº, tempo e entregador fixos); ele não reflete o pedido feito no carrinho;
* O campo de **cupom** do carrinho e a **busca** da tela inicial ainda não têm função;
* O **desconto** dos produtos em oferta é exibido como selo, mas **ainda não é aplicado ao preço** cobrado;
* O **carrinho não é esvaziado** depois de confirmar o pedido;
* O **frete** é fixo (R$ 10,99), apesar do banner de "Frete grátis acima de R$ 60";
* Os ícones da forma de pagamento são provisórios.

---

## Anexos

https://drive.google.com/drive/folders/1D5RiLn466mixH6bFV3fAX9WgHVDCdutl?usp=sharing

---

## Licença

Este projeto foi desenvolvido para fins acadêmicos e não possui finalidade comercial.