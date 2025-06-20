package com.example.pexelsapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.Collection
import com.example.pexelsapp.domain.PexelsAppRepository
import com.example.pexelsapp.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: PexelsAppRepository
) : ViewModel() {

    private val _photos = MutableStateFlow<List<Photo>?>(null)
    val photos: StateFlow<List<Photo>?>
        get() = _photos.asStateFlow()

    private val _collections = MutableStateFlow<List<Collection>?>(null)
    val collections: StateFlow<List<Collection>?>
        get() = _collections.asStateFlow()

    private var _searchValue = MutableStateFlow<String?>(null)
    val searchValue = _searchValue.asStateFlow()

    init {
        viewModelScope.launch {
            _photos.value = repository.getPhotos()
            _collections.value = repository.getFeaturedCollections()
        }
    }

    fun onSearchValue(text: String) {
        _searchValue.value = text
        viewModelScope.launch {
            _photos.value = repository.getSearchPhotos(text)
        }
    }
}