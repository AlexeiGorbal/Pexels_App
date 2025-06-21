package com.example.pexelsapp.screens.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.Collection
import com.example.pexelsapp.domain.PexelsAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: PexelsAppRepository
) : ViewModel() {

    private var firstCollection = ""

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _state = MutableStateFlow<HomeScreenState?>(null)
    val state = _state.asStateFlow()

    private var _searchValue = MutableStateFlow<String?>(null)
    val searchValue = _searchValue.asStateFlow()

    init {
        loadPhotos()
    }

    fun search(text: String) {
        _searchValue.value = text
        _uiState.value = UiState.Founding

        if (text.isNotEmpty()) {
            chooseCollection(text)
            searchPhotos(text)
        } else {
            loadPhotos()
        }
    }

    fun onExplore() {
        search(firstCollection)
    }

    fun tryAgain() {
        val currentInput = _searchValue.value ?: return
        search(currentInput)
    }

    private fun chooseCollection(text: String) {
        val prevState = _state.value ?: return
        val currentListCollections = _state.value?.collections ?: return
        val newCurrentListCollections = mutableListOf<CollectionItem>()

        currentListCollections.forEach { collection ->
            if (
                collection.title.lowercase().split(" ") ==
                text.lowercase().split(" ")
            ) {
                newCurrentListCollections.add(CollectionItem(collection.title, Color.Green))
            } else {
                newCurrentListCollections.add(collection.copy(color = Color.LightGray))
            }
        }
        _state.value = prevState.copy(collections = newCurrentListCollections)
    }

    private fun searchPhotos(text: String) {
        val prevState = _state.value ?: return

        viewModelScope.launch {
            val listPhotos = repository.getSearchPhotos(text)
            if (listPhotos.isNotEmpty()) {
                val currentState = _state.value ?: return@launch
                _state.value = prevState.copy(photos = listPhotos)
                _uiState.value = UiState.Loaded(currentState)
            } else {
                _uiState.value = UiState.NotFound
            }
        }
    }

    private fun convertCollectionsItems(collection: List<Collection>): List<CollectionItem> {
        val collectionsItemsList = mutableListOf<CollectionItem>()
        collection.forEach {
            collectionsItemsList.add(CollectionItem(it.title))
        }
        return collectionsItemsList
    }

    private fun loadPhotos() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val collections = repository.getFeaturedCollections()
            firstCollection = collections[0].title
            _state.value =
                HomeScreenState(repository.getPhotos(), convertCollectionsItems(collections))
            try {
                val currentState = _state.value ?: return@launch
                _uiState.value = UiState.Loaded(currentState)
            } catch (e: Exception) {
                _uiState.value = UiState.Error
            }
        }
    }
}