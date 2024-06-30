package com.jukco.waitforme.ui.store_list

import com.jukco.waitforme.data.network.model.StoreDto

sealed interface StoreListUiState {
    object Success : StoreListUiState
    object Error : StoreListUiState
    object Loading : StoreListUiState
}