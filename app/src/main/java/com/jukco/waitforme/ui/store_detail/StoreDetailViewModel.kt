package com.jukco.waitforme.ui.store_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.StoreDetailResponse
import com.jukco.waitforme.data.repository.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface StoreDetailUiState {
    data class Success(val storeDetailResponse: StateFlow<StoreDetailResponse>) : StoreDetailUiState
    object Error : StoreDetailUiState
    object Loading: StoreDetailUiState
}
class StoreDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val storeRepository: StoreRepository,
) : ViewModel() {
    private val storeId: Int? = savedStateHandle["storeId"]

    var storeDetailUiState: StoreDetailUiState by mutableStateOf(StoreDetailUiState.Loading)
        private set
    private val _storeDetail = MutableStateFlow(StoreDetailResponse())


    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            storeDetailUiState = try {
                _storeDetail.value = storeRepository.getStore(storeId ?: -1)
                StoreDetailUiState.Success(_storeDetail.asStateFlow())
            } catch (e: IOException) {
                StoreDetailUiState.Error
            }
        }
    }

    fun onClickBookmark() {
        _storeDetail.update {currentStore ->
            currentStore.copy(
                isFavorite = !currentStore.isFavorite
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = this.createSavedStateHandle()
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val storeRepository = application.container.storeRepository
                StoreDetailViewModel(savedStateHandle, storeRepository)
            }
        }
    }
}