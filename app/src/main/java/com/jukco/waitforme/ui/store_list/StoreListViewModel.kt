package com.jukco.waitforme.ui.store_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.ShopSorter
import com.jukco.waitforme.data.network.model.StoreDto
import com.jukco.waitforme.data.repository.StoreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class StoreListViewModel(
    private val storeRepository: StoreRepository,
) : ViewModel() {
    var storeListUiState: StoreListUiState by mutableStateOf(StoreListUiState.Loading)
        private set
    private val _ongoingStores = MutableStateFlow<PagingData<StoreDto>?>(null)
    private val _upcomingStores = MutableStateFlow<PagingData<StoreDto>?>(null)
    val ongoingStores: Flow<PagingData<StoreDto>> get() = _ongoingStores.filterNotNull()
    val upcomingStores: Flow<PagingData<StoreDto>> get() = _upcomingStores.filterNotNull()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            storeListUiState = try {
                _ongoingStores.value = async {
                    storeRepository.getStoreList(sorter = ShopSorter.DEADLINE).cachedIn(viewModelScope).first()
                }.await()
                _upcomingStores.value = async {
                    storeRepository.getStoreList(sorter = ShopSorter.NEWEST).cachedIn(viewModelScope).first()
                }.await()
                StoreListUiState.Success
            } catch (e: IOException) {
                StoreListUiState.Error
            } catch (e: HttpException) {
                StoreListUiState.Error
            }
        }
    }

    fun checkBookmark(id: Int) {
//        if (storeListUiState is StoreListUiState.Success) {
//            val index = _ongoingStores.indexOf(storeResponse)
//            _ongoingStores[index] = storeResponse.copy(isFavorite = !storeResponse.isFavorite)
//        }
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
