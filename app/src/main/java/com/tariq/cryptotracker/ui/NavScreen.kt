package com.tariq.cryptotracker.ui

sealed class NavScreen(val route:String) {
    object HomeScreen:NavScreen("home_screen")
    object DetailScreen:NavScreen("coin_detail_screen")

}
