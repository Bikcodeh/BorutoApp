package com.bikcodeh.borutoapp.domain.repository

import androidx.paging.PagingData
import com.bikcodeh.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}