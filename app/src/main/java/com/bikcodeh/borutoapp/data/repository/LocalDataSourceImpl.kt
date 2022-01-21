package com.bikcodeh.borutoapp.data.repository

import com.bikcodeh.borutoapp.data.local.BorutoDatabase
import com.bikcodeh.borutoapp.domain.model.Hero
import com.bikcodeh.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase): LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}