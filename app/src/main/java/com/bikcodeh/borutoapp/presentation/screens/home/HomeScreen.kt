package com.bikcodeh.borutoapp.presentation.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bikcodeh.borutoapp.navigation.Screen
import com.bikcodeh.borutoapp.presentation.common.ListContent
import com.bikcodeh.borutoapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(topBar = {
        HomeTopBar(onSearchClicked = {
            navController.navigate(Screen.Search.route)
        })
    }, content = {
        ListContent(heroes = allHeroes, navController = navController)
    })
}

