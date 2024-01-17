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
}