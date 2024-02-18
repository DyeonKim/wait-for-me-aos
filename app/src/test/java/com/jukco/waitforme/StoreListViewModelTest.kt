package com.jukco.waitforme

import com.jukco.waitforme.fake.FakeDataSource
import com.jukco.waitforme.fake.FakeStoreRepository
import com.jukco.waitforme.rule.TestDispatcherRule
import com.jukco.waitforme.ui.store_list.StoreListUiState
import com.jukco.waitforme.ui.store_list.StoreListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StoreListViewModelTest {
    private lateinit var viewModel: StoreListViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        viewModel = StoreListViewModel(storeRepository = FakeStoreRepository())
    }

    @Test
    fun storeViewModel_init_verifyStoreListUiStateSuccess() = runTest {
        assertThat(viewModel.storeListUiState).isInstanceOf(StoreListUiState.Success::class.java)
        assertThat(viewModel.storeListUiState).usingRecursiveComparison().isEqualTo(
            StoreListUiState.Success(
                ongoingStores = MutableStateFlow(FakeDataSource.storeList).asStateFlow(),
                upcomingStores = FakeDataSource.storeList
            )
        )
    }

//    @Test
//    fun storeViewModel_refresh_verifyStoreListUiStateError() {
//
//    }
}