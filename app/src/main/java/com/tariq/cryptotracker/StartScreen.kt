package com.tariq.cryptotracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tariq.cryptotracker.ui.NavScreen
import com.tariq.cryptotracker.ui.coin_detail.CoinDetailScreen
import com.tariq.cryptotracker.ui.coin_list.CoinListScreen
import com.tariq.cryptotracker.util.AppTitleViewModel
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    appTitleViewModel: AppTitleViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    backStackEntry?.destination?.route ?: NavScreen.HomeScreen.route

    appTitleViewModel.title = "Crypto Tracker"

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = appTitleViewModel.title) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "back Button"
                            )
                        }
                    }
                },
            )
        } // close top bar
    ) { values ->
        NavHost(
            navController = navController,
            startDestination = NavScreen.HomeScreen.route
        ) {
            composable(route = NavScreen.HomeScreen.route) {
                CoinListScreen(
                    navController = navController,
                    paddingValues = values
                )
            }
            composable(route = NavScreen.DetailScreen.route + "/{coinId}") {
                CoinDetailScreen(paddingValues = values, appTitleViewModel = appTitleViewModel)
            }
        }
    }

}