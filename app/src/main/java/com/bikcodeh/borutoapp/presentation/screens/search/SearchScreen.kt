package com.bikcodeh.borutoapp.presentation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bikcodeh.borutoapp.presentation.common.ListContent

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        },
        content = {
            ListContent(heroes = heroes, navController = navHostController)
        }
    )
}