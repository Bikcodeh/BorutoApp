package com.bikcodeh.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import androidx.test.core.app.ApplicationProvider
import com.bikcodeh.borutoapp.data.local.BorutoDatabase
import com.bikcodeh.borutoapp.data.remote.FakeBorutoApi2
import com.bikcodeh.borutoapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var borutoApi: FakeBorutoApi2
    private lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setUp() {
        borutoApi = FakeBorutoApi2()
        borutoDatabase = BorutoDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun tearDown() {
        borutoDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )

        val pagingState = PagingState<Int, Hero>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 3),
            leadingPlaceholderCount = 0
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is MediatorResult.Success)
        assertFalse((result as MediatorResult.Success).endOfPaginationReached)
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() = runBlocking {
        borutoApi.clearData()
        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )

        val pagingState = PagingState<Int, Hero>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 3),
            leadingPlaceholderCount = 0
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runBlocking {
        borutoApi.addException()

        val remoteMediator = HeroRemoteMediator(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )

        val pagingState = PagingState<Int, Hero>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 3),
            leadingPlaceholderCount = 0
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is MediatorResult.Error)

    }
}