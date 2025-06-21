package com.example.pexelsapp.screens.home

sealed class UiState {

    data object NotFound : UiState()

    data object Error : UiState()

    data object Loading : UiState()

    data object Founding : UiState()

    data class Loaded(val state: HomeScreenState) : UiState()
}