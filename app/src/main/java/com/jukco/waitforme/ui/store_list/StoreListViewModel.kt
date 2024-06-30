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
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.ShopSorter
import com.jukco.waitforme.data.network.model.StoreDto
import com.jukco.waitforme.data.repository.BookmarkRepository
import com.jukco.waitforme.data.repository.StoreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class StoreListViewModel(
    private val storeRepository: StoreRepository,
    private val bookmarkRepository: BookmarkRepository,
) : ViewModel() {
    var storeListUiState: StoreListUiState by mutableStateOf(StoreListUiState.Loading)
        private set
    private val _ongoingStores = MutableStateFlow<PagingData<StoreDto>?>(null)
    private val _upcomingStores = MutableStateFlow<PagingData<StoreDto>?>(null)

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
                StoreListUiState.Success(_ongoingStores.filterNotNull(), _upcomingStores.filterNotNull())
            } catch (e: IOException) {
                StoreListUiState.Error
            } catch (e: HttpException) {
                StoreListUiState.Error
            }
        }
    }

    fun checkBookmark(shopId: Int) {
        viewModelScope.launch {
            try {
                val response = bookmarkRepository.postBookmark(shopId)
                _ongoingStores.update { pagingData ->
                    pagingData?.map { store ->
                        if (store.id == shopId) {
                            store.copy(isFavorite = response.body()!!)
                        } else {
                            store
                        }
                    }
                }

            } catch (e: IOException) {
                // TODO: 실패 안내
            } catch (e: HttpException) {
                // TODO: 실패 안내
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val storeRepository = application.container.storeRepository
                val bookmarkRepository = application.container.bookmarkRepository
                StoreListViewModel(storeRepository, bookmarkRepository)
            }
        }
    }
}
