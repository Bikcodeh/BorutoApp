package com.bikcodeh.borutoapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.bikcodeh.borutoapp.R
import com.bikcodeh.borutoapp.ui.theme.Purple500
import com.bikcodeh.borutoapp.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController) {
    Splash()
}

@Composable
fun Splash() {

    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 300
            )
        )
    }

    if (isSystemInDarkTheme()) {
        SplashContent(modifier = Modifier.background(Color.Black), degrees = degrees.value)
    } else {
        SplashContent(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    listOf(
                        Purple700,
                        Purple500
                    )
                )
            ),
            degrees = degrees.value
        )
    }
}

@Composable
fun SplashContent(modifier: Modifier, degrees: Float) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash()
}