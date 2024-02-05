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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface StoreListUiState {
    data class Success(
        val ongoingStores: StateFlow<List<StoreResponse>>,
        val upcomingStores: List<StoreResponse>,
    ) : StoreListUiState
    object Error : StoreListUiState
    object Loading : StoreListUiState
}
class StoreListViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    var storeListUiState: StoreListUiState by mutableStateOf(StoreListUiState.Loading)
        private set
    private val _ongoingStores = MutableStateFlow<MutableList<StoreResponse>>(mutableListOf())

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            storeListUiState = try {
                val ongoing = async { storeRepository.getStoreList() }.await()
                val upcoming = async { storeRepository.getStoreList() }.await()
                _ongoingStores.value = ongoing.toMutableList()
                StoreListUiState.Success(_ongoingStores.asStateFlow(), upcoming)
            } catch (e: IOException) {
                StoreListUiState.Error
            }
        }
    }

    fun checkBookmark(id: Int) {
        if (storeListUiState is StoreListUiState.Success) {
            _ongoingStores.update { currentStateList ->
                currentStateList.map {
                    if (it.id == id) it.copy(isFavorite = !it.isFavorite)
                    else it
                }.toMutableList()
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
