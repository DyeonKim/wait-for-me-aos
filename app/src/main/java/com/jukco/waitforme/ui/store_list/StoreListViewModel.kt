package com.jukco.waitforme.ui.store_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.StoreResponse
import com.jukco.waitforme.data.repository.StoreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface StoreListUiState {
    data class Success(val ongoingStores: List<StoreResponse>, val upcomingStores: List<StoreResponse>) : StoreListUiState
    object Error : StoreListUiState
    object Loading : StoreListUiState
}
class StoreListViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    var storeListUiState: StoreListUiState by mutableStateOf(StoreListUiState.Loading)
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            storeListUiState = try {
                val ongoing = async { storeRepository.getStoreList() }.await()
                val upcoming = async { storeRepository.getStoreList() }.await()
                StoreListUiState.Success(ongoing, upcoming)

            } catch (e: IOException) {
                StoreListUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val storeRepository = application.container.storeRepository
                StoreListViewModel(storeRepository)
            }
        }
    }
}