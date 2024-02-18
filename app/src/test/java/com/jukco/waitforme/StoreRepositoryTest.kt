package com.jukco.waitforme

import com.jukco.waitforme.data.repository.StoreRepositoryImplementation
import com.jukco.waitforme.fake.FakeDataSource
import com.jukco.waitforme.fake.FakeStoreApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class StoreRepositoryTest {
    @Test
    fun storeRepository_getStoreList_verifyStoreList() = runTest {
        val repository = StoreRepositoryImplementation(storeApi = FakeStoreApi())
        assertEquals(FakeDataSource.storeList, repository.getStoreList())
    }

    @Test
    fun storeRepository_getStore_verifyStoreList() = runTest {
        val repository = StoreRepositoryImplementation(storeApi = FakeStoreApi())
        val storeId1 = 5
        val storeId2 = 7
        assertEquals(FakeDataSource.storeDetailList[storeId1], repository.getStore(storeId1))
        assertEquals(FakeDataSource.storeDetailList[storeId2], repository.getStore(storeId2))
    }
}