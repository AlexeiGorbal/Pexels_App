package com.example.pexelsapp.screens.ditails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.PexelsAppRepository
import com.example.pexelsapp.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: PexelsAppRepository
) : ViewModel() {

    private val _photo = MutableStateFlow<Photo?>(null)
    val photo: StateFlow<Photo?>
        get() = _photo.asStateFlow()

    fun getPhotoById(photoId: Long) {
        viewModelScope.launch {
            _photo.value = repository.getPhotoById(photoId)
        }
    }
}