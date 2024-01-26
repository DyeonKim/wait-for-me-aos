package com.jukco.waitforme

import androidx.lifecycle.SavedStateHandle
import com.jukco.waitforme.fake.FakeDataSource
import com.jukco.waitforme.fake.FakeStoreRepository
import com.jukco.waitforme.rule.TestDispatcherRule
import com.jukco.waitforme.ui.store_detail.StoreDetailUiState
import com.jukco.waitforme.ui.store_detail.StoreDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StoreDetailViewModelTest {
    private lateinit var viewModel: StoreDetailViewModel
    private val storeId = 5

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        val savedState = SavedStateHandle(mapOf("storeId" to storeId))
        viewModel = StoreDetailViewModel(
            storeRepository = FakeStoreRepository(),
            savedStateHandle = savedState
        )
    }

    @Test
    fun storeDetailViewModel_init_verifyStoreListUiStateSuccess() = runTest {
        assertThat(viewModel.storeDetailUiState).isInstanceOf(StoreDetailUiState.Success::class.java)
        assertThat(viewModel.storeDetailUiState).usingRecursiveComparison().isEqualTo(
            StoreDetailUiState.Success(
                storeDetailResponse = MutableStateFlow(FakeDataSource.storeDetailList[storeId]).asStateFlow()
            )
        )
    }
}