package com.bikcodeh.borutoapp.data.remote

import com.bikcodeh.borutoapp.domain.model.ApiResponse
import com.bikcodeh.borutoapp.domain.model.Hero

class FakeBorutoApi : BorutoApi {

    private val heroes = listOf(
        Hero(
            id = 1,
            name = "Naruto",
            image = "",
            about = "",
            rating = 0.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 2,
            name = "Sasuke",
            image = "",
            about = "",
            rating = 0.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 3,
            name = "Sakura",
            image = "",
            about = "",
            rating = 0.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        )
    )

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        val searchHeroes = findHeroes(name = name)
        return ApiResponse(
            success = true,
            message = "OK",
            heroes = searchHeroes
        )
    }

    private fun findHeroes(name: String): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (name.isNotEmpty()) {
            heroes.forEach { hero ->
                if (hero.name.lowercase().contains(name.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}