package com.bikcodeh.borutoapp.data.paging_source

import androidx.paging.PagingSource
import com.bikcodeh.borutoapp.data.remote.BorutoApi
import com.bikcodeh.borutoapp.data.remote.FakeBorutoApi
import com.bikcodeh.borutoapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchHeroesSourceTest {

    private lateinit var borutoApi: BorutoApi
    private lateinit var heroes: List<Hero>

    @Before
    fun setUp() {
        borutoApi = FakeBorutoApi()
        heroes = listOf(
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
    }

    @Test
    fun `Search api with existing hero name, expect single hero result, assert LoadResult_Page`() {
        runBlocking {
            val heroesSource = SearchHeroesSource(borutoApi = borutoApi, query = "Sasuke")
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(
                        heroes[1]
                    ),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }

    @Test
    fun `Search api with existing hero name, expect multiple hero result, assert LoadResult_Page`() {
        runBlocking {
            val heroesSource = SearchHeroesSource(borutoApi = borutoApi, query = "Sa")
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(
                        heroes[1],
                        heroes[2]
                    ),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }

    @Test
    fun `Search api with non existing hero name, assert empty heroes list and LoadResult_Page`() {
        runBlocking {
            val heroesSource = SearchHeroesSource(borutoApi = borutoApi, query = "")

            val loadResult = heroesSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = borutoApi.searchHeroes("").heroes
            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
    }
}