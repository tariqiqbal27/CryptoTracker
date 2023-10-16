package com.tariq.cryptotracker.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppTitleViewModel @Inject constructor() : ViewModel() {
    var title by mutableStateOf("Crypto Tracker")
}