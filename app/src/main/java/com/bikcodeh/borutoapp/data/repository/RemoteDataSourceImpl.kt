package com.bikcodeh.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bikcodeh.borutoapp.data.local.BorutoDatabase
import com.bikcodeh.borutoapp.data.paging_source.HeroRemoteMediator
import com.bikcodeh.borutoapp.data.remote.BorutoApi
import com.bikcodeh.borutoapp.domain.model.Hero
import com.bikcodeh.borutoapp.domain.repository.RemoteDataSource
import com.bikcodeh.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
): RemoteDataSource {

    private val heroDao = borutoDatabase.heroDao()

    @ExperimentalPagingApi
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}