package com.example.pexelsapp.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private val _searchValue = MutableStateFlow<String?>(null)
    val searchValue = _searchValue.asStateFlow()

    fun onSearchValue(text: String) {
        _searchValue.value = text
    }
}