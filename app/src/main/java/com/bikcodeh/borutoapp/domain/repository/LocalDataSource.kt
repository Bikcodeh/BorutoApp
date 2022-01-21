package com.bikcodeh.borutoapp.domain.repository

import com.bikcodeh.borutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}