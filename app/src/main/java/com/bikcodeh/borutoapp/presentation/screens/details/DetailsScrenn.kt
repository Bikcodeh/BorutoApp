package com.bikcodeh.borutoapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bikcodeh.borutoapp.util.Constants.BASE_URL
import com.bikcodeh.borutoapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.bikcodeh.borutoapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navHostController = navHostController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UIEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )

                    if (bitmap != null) {
                        detailsViewModel.setColorsPalette(
                            colors = extractColorsFromBitmap(bitmap = bitmap)
                        )
                    }
                }
            }
        }
    }
}