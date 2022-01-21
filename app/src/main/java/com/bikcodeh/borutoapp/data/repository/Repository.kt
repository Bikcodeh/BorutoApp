package com.bikcodeh.borutoapp.data.repository

import androidx.paging.PagingData
import com.bikcodeh.borutoapp.domain.model.Hero
import com.bikcodeh.borutoapp.domain.repository.DataStoreOperations
import com.bikcodeh.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val remote: RemoteDataSource
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query = query)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStoreOperations.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStoreOperations.readOnBoardingState()
    }
}