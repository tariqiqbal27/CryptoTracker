package com.tariq.cryptotracker.ui.coin_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tariq.cryptotracker.domain.model.Coin
import com.tariq.cryptotracker.ui.NavScreen
import com.tariq.cryptotracker.ui.theme.darkGreen
import com.tariq.cryptotracker.ui.theme.textBody1

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
    paddingValues: PaddingValues = PaddingValues(5.dp)
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = paddingValues
        ) {
            items(state.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(NavScreen.DetailScreen.route + "/${coin.id}")
                    }
                )
            }
        }
        if (state.isLoading)
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

    }

}

@Preview(showBackground = true)
@Composable
fun CoinListPreview() {
    CoinListItem(
        coin = Coin(
            id = "btc",
            name = "Bitcoin",
            icon = "",
            price = 26891.006,
            priceChanged = 0.3,
            rank = 1,
            symbol = "BTC"
        ),
        onItemClick = { }
    )
}


@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .fillMaxWidth()
            .clickable { onItemClick(coin) },
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Column {
                AsyncImage(
                    modifier = Modifier.size(50.dp),
                    model = coin.icon,
                    contentDescription = coin.name,
                )
            }
            Column(Modifier.padding(horizontal = 15.dp)) {
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
                Text(
                    text = coin.symbol,
                    color = textBody1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = End
            ) {
                Text(
                    text = String.format("$%.3f", coin.price),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = if (coin.priceChanged > 0) "+${coin.priceChanged}%" else "${coin.priceChanged}%",
                    color = if (coin.priceChanged > 0) darkGreen else Color.Red,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}