package com.bikcodeh.borutoapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bikcodeh.borutoapp.presentation.common.ListContent

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(topBar = {
        HomeTopBar(onSearchClicked = {})
    }, content = {
        ListContent(heroes = allHeroes, navController = navController)
    })
}

