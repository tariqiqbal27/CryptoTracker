package com.tariq.cryptotracker.ui.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tariq.cryptotracker.domain.model.CoinDetail
import com.tariq.cryptotracker.ui.theme.darkGreen
import com.tariq.cryptotracker.util.AppTitleViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    appTitleViewModel: AppTitleViewModel
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingValues)
    ) {
        state.coin?.let { coin ->
            LaunchedEffect(Unit) {
                appTitleViewModel.title = coin.symbol
            }

            CoinScreen(coin = coin)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCoinDetail() {
    val coin = CoinDetail(
        "13", "", "Bitcoin", "BTC", 1, 1000.34, 34445.44, .2, 1.1, .4, "555", "33"
    )
    CoinScreen(coin = coin)
}

@Composable
fun CoinScreen(coin: CoinDetail) {
    val price1h = coin.priceChange1h
    val price1d = coin.priceChange1d
    val price1w = coin.priceChange1w
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row {
            Column {
                Text(
                    text = "PRICE",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$${coin.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Divider()
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = "PRICE CHANGED",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "1 Hour",
                            fontWeight = FontWeight.W500
                        )
                        Text(
                            text = if (price1h > 0) "\u25B2 ${price1h}%" else "\u25BC ${price1h}%",
                            color = if (price1h > 0) darkGreen else Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "1 Day",
                            fontWeight = FontWeight.W500
                        )
                        Text(
                            text = if (price1d > 0) "\u25B2 ${price1d}%" else "\u25BC ${price1d}%",
                            color = if (price1d > 0) darkGreen else Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "1 Week ",
                            fontWeight = FontWeight.W500
                        )
                        Text(
                            text = if (price1w > 0) "\u25B2 ${price1w}%" else "\u25BC ${price1w}%",
                            color = if (price1w > 0) darkGreen else Color.Red,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Divider()

            }

        }

    }
}