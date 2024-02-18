package com.jukco.waitforme

import com.jukco.waitforme.fake.FakeDataSource
import com.jukco.waitforme.fake.FakeStoreRepository
import com.jukco.waitforme.rule.TestDispatcherRule
import com.jukco.waitforme.ui.store_list.StoreListUiState
import com.jukco.waitforme.ui.store_list.StoreListViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class StoreViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun storeViewModel_refresh_verifyStoreListUiStateSuccess() = runTest {
        val viewModel = StoreListViewModel(storeRepository = FakeStoreRepository())
        assertEquals(
            StoreListUiState.Success(
                ongoingStores = FakeDataSource.storeList,
                upcomingStores = FakeDataSource.storeList
            ),
            viewModel.storeListUiState
        )
    }

//    @Test
//    fun storeViewModel_refresh_verifyStoreListUiStateError() {
//
//    }
}